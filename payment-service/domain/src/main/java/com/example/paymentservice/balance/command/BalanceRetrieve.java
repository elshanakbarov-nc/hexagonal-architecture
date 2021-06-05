package com.example.paymentservice.balance.command;

import com.example.paymentservice.common.model.Command;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BalanceRetrieve implements Command {

    private Long accountId;

    public static BalanceRetrieve from(Long accountId){
        return BalanceRetrieve.builder().accountId(accountId).build();
    }

}
