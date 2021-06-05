package com.example.paymentservice.adapters.balance.rest.dto;

import com.example.paymentservice.balance.model.Balance;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BalanceResponse {
    private Long id;
    private Long accountId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BalanceResponse fromModel(Balance balance){
        return BalanceResponse.builder()
                .accountId(balance.getAccountId())
                .amount(balance.getAmount())
                .createdAt(balance.getCreatedAt())
                .updatedAt(balance.getUpdatedAt())
                .build();
    }
}
