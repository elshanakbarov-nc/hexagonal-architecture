package com.example.paymentservice.balance;

import com.example.paymentservice.balance.command.BalanceTransactionCreate;
import com.example.paymentservice.balance.model.Balance;
import com.example.paymentservice.common.DomainComponent;
import com.example.paymentservice.common.exception.PaymentApiBusinessException;
import org.springframework.stereotype.Component;

@DomainComponent
public class BalanceValidator {

    public static void validate(Balance balance, BalanceTransactionCreate balanceTransactionCreate){
        if (!balance.isSufficient(balanceTransactionCreate.getAmountAsNumeric())){
            throw new PaymentApiBusinessException("paymentapi.balance.notSufficient");
        }
    }

}
