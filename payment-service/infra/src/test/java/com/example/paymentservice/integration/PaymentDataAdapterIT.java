package com.example.paymentservice.integration;

import com.example.paymentservice.IT;
import com.example.paymentservice.adapters.payment.jpa.PaymentAdapter;
import com.example.paymentservice.adapters.payment.jpa.repo.PaymentJpaRepo;
import com.example.paymentservice.payment.command.PaymentCreate;
import com.example.paymentservice.payment.model.Payment;
import com.example.paymentservice.payment.model.PaymentState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;


@IT
@Sql(scripts = "classpath:sql/payments.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:sql/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PaymentDataAdapterIT {

    @Autowired
    PaymentAdapter paymentAdapter;
    @Autowired
    PaymentJpaRepo paymentJpaRepo;

    @Test
    void it_should_retrieve_payment(){
        // given
        Long accountId = 1L;

        // when
        var payment = paymentAdapter.retrieve(accountId);

        assertThat(payment).isNotNull()
                .returns(1L, from(Payment::getId));
    }

    @Test
    void it_should_create_payment(){
        // given
        PaymentCreate paymentCreate = PaymentCreate.builder()
                .accountId(100L)
                .price(BigDecimal.valueOf(100.0))
                .referenceCode("ref1")
                .build();
        //when
        Payment payment = paymentAdapter.create(paymentCreate);

        //then
        assertThat(payment).isNotNull().isNotNull()
                .returns(100L, from(Payment::getAccountId))
                .returns(PaymentState.SUCCESS, from(Payment::getPaymentState));

    }

}

