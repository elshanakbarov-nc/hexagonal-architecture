package com.example.paymentservice.integration;

import com.example.commons.rest.Response;
import com.example.paymentservice.AbstractIT;
import com.example.paymentservice.IT;
import com.example.paymentservice.adapters.payment.rest.dto.PaymentCreateRequest;
import com.example.paymentservice.adapters.payment.rest.dto.PaymentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@IT
public class PaymentControllerIT extends AbstractIT {
    private final ParameterizedTypeReference<Response<PaymentResponse>> paymentResponseType = new ParameterizedTypeReference<>() {
    };

    @Test
    void it_should_make_payment(){
        doPayment(1L, "10.0", "10.0");
    }

    private void doPayment(Long accountId, String price, String lastBalance){
        doPayment(accountId, price, lastBalance, HttpStatus.CREATED, null);
    }

    private void doPayment(Long accountId, String price, String lastBalance, HttpStatus httpStatus, String errorCode){
        // given
        PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
                .accountId(accountId)
                .price(BigDecimal.valueOf(80.00))
                .referenceCode("ref1")
                .build();

        ResponseEntity<Response<PaymentResponse>> response1 = testRestTemplate.exchange(
                "/api/v1/payments",
                HttpMethod.POST, new HttpEntity<>(paymentCreateRequest, null), paymentResponseType);

        assertThat(response1).isNotNull();
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response1.getBody()).isNotNull();

        PaymentResponse paymentResponse = response1.getBody().getData();
        assertThat(paymentResponse)
                .extracting("accountId","price","referenceCode")
                .contains(accountId, new BigDecimal(price), "ref1");
    }
}
