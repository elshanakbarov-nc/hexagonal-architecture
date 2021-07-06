package com.example.paymentservice.common.commandhandler;

import com.example.commons.commandhandler.CommandHandler;
import com.example.paymentservice.common.exception.PaymentApiBusinessException;
import com.example.paymentservice.payment.command.PaymentCreate;
import com.example.paymentservice.payment.model.Payment;
import com.example.paymentservice.payment.model.PaymentState;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@ConditionalOnProperty(name = "commandhandler.enabled", havingValue = "false", matchIfMissing = true)
public class FakePaymentCreateCommandHandler implements CommandHandler<PaymentCreate, Payment> {

    private static final Long PAYMENT_FAIL_ACCOUNT_ID = 6661L;
    private static final List<Long> FAILING_IDS = List.of(
            PAYMENT_FAIL_ACCOUNT_ID
    );


    private void failedPaymentScenario(PaymentCreate paymentCreate) {
        if (paymentCreate.getAccountId().equals(PAYMENT_FAIL_ACCOUNT_ID)) {
            throw new PaymentApiBusinessException("paymentapi.balance.notSufficient");
        }
    }

    private Payment succeededPaymentCreateScenario(PaymentCreate paymentCreate) {
        if (!FAILING_IDS.contains(paymentCreate.getAccountId())) {
            return Payment.builder()
                    .id(1L)
                    .accountId(paymentCreate.getAccountId())
                    .paymentState(PaymentState.SUCCESS)
                    .createdAt(LocalDateTime.of(2021,1,1,19,0,0))
                    .price(BigDecimal.valueOf(10.0))
                    .referenceCode("ref1")
                    .build();
        }
        throw new RuntimeException("uncovered test scenario occurred");
    }

    @Override
    public Payment handle(PaymentCreate paymentCreate) {
        failedPaymentScenario(paymentCreate);
        return succeededPaymentCreateScenario(paymentCreate);
    }
}
