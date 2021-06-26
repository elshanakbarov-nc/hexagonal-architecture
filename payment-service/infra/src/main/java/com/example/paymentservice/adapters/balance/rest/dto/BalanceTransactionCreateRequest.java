package com.example.paymentservice.adapters.balance.rest.dto;

import com.example.paymentservice.balance.command.BalanceTransactionCreate;
import com.example.paymentservice.balance.model.BalanceTransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BalanceTransactionCreateRequest {

    private Long accountId;

    private BigDecimal amount;

    private BalanceTransactionType type;

    public BalanceTransactionCreate toCommand(){
        return BalanceTransactionCreate.builder()
                .accountId(getAccountId())
                .amount(getAmount())
                .type(getType())
                .build();
    }

}
