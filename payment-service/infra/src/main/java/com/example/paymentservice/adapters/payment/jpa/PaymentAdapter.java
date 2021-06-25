package com.example.paymentservice.adapters.payment.jpa;

import com.example.paymentservice.adapters.payment.jpa.entity.PaymentEntity;
import com.example.paymentservice.adapters.payment.jpa.repo.PaymentJpaRepo;
import com.example.paymentservice.common.model.Status;
import com.example.paymentservice.payment.command.PaymentCreate;
import com.example.paymentservice.payment.model.Payment;
import com.example.paymentservice.payment.model.PaymentState;
import com.example.paymentservice.payment.port.PaymentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentAdapter implements PaymentPort {

    private final PaymentJpaRepo paymentJpaRepo;

    @Override
    public Payment create(PaymentCreate paymentCreate) {
        var paymentEntity = new PaymentEntity();
        paymentEntity.setAccountId(paymentCreate.getAccountId());
        paymentEntity.setReferenceCode(paymentCreate.getReferenceCode());
        paymentEntity.setPrice(paymentCreate.getPrice());
        paymentEntity.setPaymentState(PaymentState.SUCCESS);
        paymentEntity.setStatus(Status.ACTIVE);

        var savedPaymentEntity = paymentJpaRepo.save(paymentEntity);

        return toModel(savedPaymentEntity);
    }

    private Payment toModel(PaymentEntity savedPaymentEntity) {
        return Payment.builder()
                .id(savedPaymentEntity.getId())
                .referenceCode(savedPaymentEntity.getReferenceCode())
                .accountId(savedPaymentEntity.getAccountId())
                .price(savedPaymentEntity.getPrice())
                .paymentState(savedPaymentEntity.getPaymentState())
                .build();
    }
}
