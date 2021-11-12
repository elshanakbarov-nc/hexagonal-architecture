package com.example.paymentservice.integration;

import com.example.paymentservice.AbstractIT;
import com.example.paymentservice.IT;
import com.example.paymentservice.adapters.balance.jpa.BalanceAdapter;
import com.example.paymentservice.adapters.balance.jpa.repo.BalanceJpaRepo;
import com.example.paymentservice.adapters.balance.jpa.repo.BalanceTransactionJpaRepo;
import com.example.paymentservice.balance.model.Balance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;

@IT
@Sql(scripts = "classpath:sql/balances.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:sql/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class BalanceDataAdepterIT extends AbstractIT {

    @Autowired
    BalanceAdapter balanceAdapter;
    @Autowired
    BalanceJpaRepo balanceJpaRepo;
    @Autowired
    BalanceTransactionJpaRepo balanceTransactionJpaRepo;


    @Test
    void it_should_retrieve_balance(){
        // given
        Long accountId = 666L;

        // when
        Balance balance = balanceAdapter.retrieve(accountId);

        // then
        assertThat(balance).isNotNull()
                .returns(1L, from(Balance::getId))
                .returns(666L,from(Balance::getAccountId))
                .returns(BigDecimal.valueOf(91.91),from(Balance::getAmount));
    }

}
