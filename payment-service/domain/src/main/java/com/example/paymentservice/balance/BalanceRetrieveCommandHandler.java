package com.example.paymentservice.balance;

import com.example.commons.commandhandler.CommandHandler;
import com.example.paymentservice.balance.command.BalanceRetrieve;
import com.example.paymentservice.balance.model.Balance;
import com.example.paymentservice.balance.port.BalancePort;
import com.example.commons.DomainComponent;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class BalanceRetrieveCommandHandler implements CommandHandler<BalanceRetrieve, Balance> {

    private final BalancePort balancePort;

    @Override
    public Balance handle(BalanceRetrieve command) {
        return balancePort.retrieve(command.getAccountId());
    }
}
