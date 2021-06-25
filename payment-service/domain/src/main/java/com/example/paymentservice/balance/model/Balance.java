package com.example.paymentservice.balance.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@With
@ToString
@EqualsAndHashCode
public class Balance {

    private Long id;
    private Long accountId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
