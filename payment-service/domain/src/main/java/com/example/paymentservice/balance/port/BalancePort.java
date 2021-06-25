package com.example.paymentservice.balance.port;

import com.example.paymentservice.balance.command.BalanceTransactionCreate;
import com.example.paymentservice.balance.model.Balance;

public interface BalancePort {
    Balance retrieve(Long accountId);

    Balance update(Balance balance, BalanceTransactionCreate balanceTransactionCreate);
}
