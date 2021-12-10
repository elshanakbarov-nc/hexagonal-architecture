package com.example.paymentservice.adapters.payment.jpa;

import com.example.commons.exception.ApiBusinessException;
import com.example.paymentservice.adapters.payment.jpa.entity.PaymentEntity;
import com.example.paymentservice.adapters.payment.jpa.repo.PaymentJpaRepo;
import com.example.commons.model.Status;
import com.example.paymentservice.payment.command.PaymentCreate;
import com.example.paymentservice.payment.model.Payment;
import com.example.paymentservice.payment.model.PaymentState;
import com.example.paymentservice.payment.port.PaymentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentAdapter implements PaymentPort {

    private final PaymentJpaRepo paymentJpaRepo;

    @Override
    public Payment create(PaymentCreate paymentCreate) {
        var paymentEntity = new PaymentEntity();
        paymentEntity.setAccountId(paymentCreate.getAccountId());
        paymentEntity.setOrderId(paymentCreate.getOrderId());
        paymentEntity.setReferenceCode(paymentCreate.getReferenceCode());
        paymentEntity.setPrice(paymentCreate.getPrice());
        paymentEntity.setPaymentState(PaymentState.SUCCESS);
        paymentEntity.setStatus(Status.ACTIVE);

        var savedPaymentEntity = paymentJpaRepo.save(paymentEntity);

        return toModel(savedPaymentEntity);
    }

    public Payment retrieve(Long accountId){
        return paymentJpaRepo.findById(accountId).map(PaymentEntity::toModel)
                .orElseThrow(() -> new ApiBusinessException("paymentapi.payment.notFound"));
    }

    private Payment toModel(PaymentEntity savedPaymentEntity) {
        return Payment.builder()
                .id(savedPaymentEntity.getId())
                .orderId(savedPaymentEntity.getOrderId())
                .referenceCode(savedPaymentEntity.getReferenceCode())
                .accountId(savedPaymentEntity.getAccountId())
                .price(savedPaymentEntity.getPrice())
                .paymentState(savedPaymentEntity.getPaymentState())
                .build();
    }
}
