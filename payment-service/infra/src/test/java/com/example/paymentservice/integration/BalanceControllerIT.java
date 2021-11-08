package com.example.paymentservice.integration;

import com.example.commons.rest.Response;
import com.example.paymentservice.AbstractIT;
import com.example.paymentservice.IT;
import com.example.paymentservice.adapters.balance.rest.dto.BalanceResponse;
import com.example.paymentservice.adapters.balance.rest.dto.BalanceTransactionCreateRequest;
import com.example.paymentservice.balance.model.BalanceTransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        assertThat(response.getBody()).isNotNull();

        var balanceResponse = response.getBody().getData();
        assertThat(balanceResponse)
                .extracting("accountId","amount","id")
                .contains(accountId, BigDecimal.valueOf(10.0),1L);
    }


    @Test
    void should_deposit_withdraw_balance() {
        updateBalance(1L, BalanceTransactionType.DEPOSIT, 50.0, 50.0);
        updateBalance(1L, BalanceTransactionType.DEPOSIT, 10.0, 60.0);
        updateBalance(1L, BalanceTransactionType.WITHDRAW, 10.0, 50.0);
    }

    private void updateBalance(Long accountId, BalanceTransactionType balanceTransactionType, double amount, double expectedAmount) {
        // given
        BalanceTransactionCreateRequest balanceTransactionCreateRequest = BalanceTransactionCreateRequest.builder()
                .accountId(accountId)
                .type(balanceTransactionType)
                .amount(BigDecimal.valueOf(amount))
                .build();

        //when
        ResponseEntity<Response<BalanceResponse>> response1 = testRestTemplate.exchange(
                "/api/v1/balances",
                HttpMethod.POST, new HttpEntity<>(balanceTransactionCreateRequest, null), balanceResponseType);

        //then
        assertThat(response1).isNotNull();
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response1.getBody()).isNotNull();

        assertThat(response1.getBody().getData()).isNotNull();
        BalanceResponse balanceResponse1 = response1.getBody().getData();
        assertThat(balanceResponse1)
                .extracting("accountId", "amount")
                .contains(accountId, BigDecimal.valueOf(expectedAmount));
    }

}
