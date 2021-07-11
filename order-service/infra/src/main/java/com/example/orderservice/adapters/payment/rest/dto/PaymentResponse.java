package com.example.orderservice.adapters.payment.rest.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private Long id;
    private Long accountId;
    private BigDecimal price;
    private String referenceCode;
}
