package com.example.paymentservice.integration;

import com.example.paymentservice.AbstractIT;
import com.example.paymentservice.IT;
import com.example.paymentservice.adapters.balance.rest.dto.BalanceResponse;
import com.example.paymentservice.common.rest.Response;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


@IT
public class BalanceControllerIT extends AbstractIT {

    private final ParameterizedTypeReference<Response<BalanceResponse>> balanceResponseType = new ParameterizedTypeReference<>() {
    };

    @Test
    void it_should_retrieve_balance_by_account_id(){
        // given
        Long accountId = 1L;
        // when
        var response = testRestTemplate.exchange("/api/v1/balances?accountId=" + accountId,
                HttpMethod.GET, new HttpEntity<>(null, null), balanceResponseType);
       // then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        var balanceResponse = response.getBody().getData();
        assertThat(balanceResponse)
                .extracting("accountId","amount","id")
                .contains(accountId, BigDecimal.valueOf(10.0),1L);
    }



}
