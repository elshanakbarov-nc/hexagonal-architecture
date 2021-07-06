package com.example.paymentservice.payment;

import com.example.paymentservice.balance.BalanceValidator;
import com.example.paymentservice.balance.command.BalanceRetrieve;
import com.example.paymentservice.balance.command.BalanceTransactionCreate;
import com.example.paymentservice.balance.model.Balance;
import com.example.paymentservice.balance.model.BalanceTransactionType;
import com.example.commons.DomainComponent;
import com.example.commons.commandhandler.CommandHandler;
import com.example.paymentservice.payment.command.PaymentCreate;
import com.example.paymentservice.payment.model.Payment;
import com.example.paymentservice.payment.port.PaymentPort;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class PaymentCreateCommandHandler implements CommandHandler<PaymentCreate, Payment> {

    private final CommandHandler<BalanceRetrieve, Balance> balanceRetrieveCommandHandler;
    private final CommandHandler<BalanceTransactionCreate, Balance> balanceTransactionCreateCommandHandler;
    private final PaymentPort paymentPort;

    @Override
    public Payment handle(PaymentCreate paymentCreate) {
        var balanceTransactionCreate = buildBalanceTransactionCreate(paymentCreate);
        var balance = balanceRetrieveCommandHandler.handle(BalanceRetrieve.from(paymentCreate.getAccountId()));

        BalanceValidator.validate(balance,balanceTransactionCreate);

        var payment = paymentPort.create(paymentCreate);
        balanceTransactionCreateCommandHandler.handle(balanceTransactionCreate);

        log.info("Total {} paid from {}", paymentCreate.getPrice(), paymentCreate.getAccountId());

        return payment;
    }

    private BalanceTransactionCreate buildBalanceTransactionCreate(PaymentCreate paymentCreate) {
        return BalanceTransactionCreate.builder()
                .accountId(paymentCreate.getAccountId())
                .amount(paymentCreate.getPrice())
                .type(BalanceTransactionType.WITHDRAW)
                .build();
    }
}
