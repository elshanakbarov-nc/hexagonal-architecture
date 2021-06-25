package com.example.paymentservice.payment.model;

import com.example.paymentservice.common.model.Command;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
@With
@EqualsAndHashCode
public class Payment implements Command {

    private Long id;
    private LocalDateTime createdAt;
    private Long accountId;
    private BigDecimal price;
    private String referenceCode;
    private PaymentState paymentState;

}
