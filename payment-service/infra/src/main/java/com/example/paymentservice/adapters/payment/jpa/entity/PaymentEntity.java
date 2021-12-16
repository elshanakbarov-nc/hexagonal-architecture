package com.example.paymentservice.adapters.payment.jpa.entity;

import com.example.commons.entity.AbstractEntity;
import com.example.paymentservice.payment.model.Payment;
import com.example.paymentservice.payment.model.PaymentState;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "payment")
@Table(name = "payment")
@Where(clause = "status <> -1")
public class PaymentEntity extends AbstractEntity {

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String referenceCode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentState paymentState;

    public Payment toModel(){
       return Payment.builder()
                .id(super.getId())
                .accountId(accountId)
               .orderId(orderId)
               .price(price)
               .referenceCode(referenceCode)
               .paymentState(paymentState)
               .build();
    }
}

