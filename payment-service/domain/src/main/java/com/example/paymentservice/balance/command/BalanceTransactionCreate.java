package com.example.paymentservice.balance.command;

import com.example.commons.model.Command;
import com.example.paymentservice.balance.model.BalanceTransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BalanceTransactionCreate implements Command {

    private Long accountId;
    private BigDecimal amount;
    private BalanceTransactionType type;

    public BigDecimal getAmountAsNumeric(){
        return type.equals(BalanceTransactionType.WITHDRAW) ? amount.multiply(new BigDecimal("-1")) : amount;
    }

}
