package com.example.paymentservice.balance;

import com.example.paymentservice.balance.command.BalanceTransactionCreate;
import com.example.paymentservice.balance.model.Balance;
import com.example.paymentservice.balance.port.BalancePort;
import com.example.paymentservice.common.DomainComponent;
import com.example.paymentservice.common.commandhandler.CommandHandler;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class BalanceTransactionCreateCommandHandler implements CommandHandler<BalanceTransactionCreate, Balance> {

    private final BalancePort balancePort;

    @Override
    public Balance handle(BalanceTransactionCreate balanceTransactionCreate) {
        var balance = balancePort.retrieve(balanceTransactionCreate.getAccountId());
        return balancePort.update(balance, balanceTransactionCreate);
    }
}