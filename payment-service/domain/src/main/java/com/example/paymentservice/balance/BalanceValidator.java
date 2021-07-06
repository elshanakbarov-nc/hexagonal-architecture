package com.example.paymentservice.balance;

import com.example.commons.exception.ApiBusinessException;
import com.example.paymentservice.balance.command.BalanceTransactionCreate;
import com.example.paymentservice.balance.model.Balance;
import com.example.commons.DomainComponent;

@DomainComponent
public class BalanceValidator {

    public static void validate(Balance balance, BalanceTransactionCreate balanceTransactionCreate){
        if (!balance.isSufficient(balanceTransactionCreate.getAmountAsNumeric())){
            throw new ApiBusinessException("paymentapi.balance.notSufficient");
        }
    }

}
