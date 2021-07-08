package com.example.orderservice.adapters.payment.rest;

import com.example.commons.exception.ApiBusinessException;
import com.example.commons.rest.Response;
import com.example.orderservice.adapters.payment.rest.dto.PaymentCreateRequest;
import com.example.orderservice.adapters.payment.rest.dto.PaymentResponse;
import com.example.orderservice.adapters.payment.rest.properties.PaymentApiProperties;
import com.example.orderservice.payment.command.PaymentCreate;
import com.example.orderservice.payment.model.Payment;
import com.example.orderservice.payment.port.PaymentPort;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;



@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "adapters.payment.enabled",havingValue = "true")
public class PaymentRestAdapter implements PaymentPort {

    private final RestTemplate restTemplate;
    private final PaymentApiProperties paymentApiProperties;
    private final ParameterizedTypeReference<Response<PaymentResponse>> paymentResponseType = new ParameterizedTypeReference<>() {
    };

    @Override
    @Retryable(value = {Exception.class},
            maxAttemptsExpression = "${adapters.payment.retryAttempts}",
            backoff = @Backoff(delayExpression = "${adapters.payment.retryDelay}"))
    public Payment pay(PaymentCreate paymentCreate) {
        var paymentCreateRequest = PaymentCreateRequest.builder()
                .accountId(paymentCreate.getAccountId())
                .price(paymentCreate.getPrice())
                .referenceCode(paymentCreate.getReferenceCode())
                .build();

        var paymentResponse = callApi(paymentCreateRequest, preparePaymentUrl());

        return Payment.builder()
                .accountId(paymentResponse.getAccountId())
                .price(paymentResponse.getPrice())
                .referenceCode(paymentResponse.getReferenceCode())
                .build();
    }

    @Recover
    public Payment pay(ApiBusinessException e, PaymentCreate paymentCreate) {
        log.error("Couldn't connect to payment api to do payment for {}", paymentCreate, e);
        throw e;
    }

    @Recover
    public Payment pay(Exception e, PaymentCreate paymentCreate) {
        log.error("Couldn't connect to payment api to do payment for {}", paymentCreate, e);
        throw new ApiBusinessException("ticketapi.payment.client.error");
    }

    private PaymentResponse callApi(PaymentCreateRequest paymentCreateRequest, String url) {
        var exchange = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(paymentCreateRequest), this.paymentResponseType);
        var body = exchange.getBody();

        if (Objects.isNull(body)) throw new ApiBusinessException("ticketapi.payment.emptyResponse");
        return body.getData();
    }

    private String preparePaymentUrl() {
        return paymentApiProperties.getBaseUrl() + paymentApiProperties.getPaymentPath();
    }
}
