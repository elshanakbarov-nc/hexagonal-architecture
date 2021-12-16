package com.example.paymentservice.payment;

import com.example.commons.exception.ApiBusinessException;
import com.example.paymentservice.balance.BalanceValidator;
import com.example.paymentservice.balance.command.BalanceRetrieve;
import com.example.paymentservice.balance.command.BalanceTransactionCreate;
import com.example.paymentservice.balance.model.Balance;
import com.example.paymentservice.balance.model.BalanceTransactionType;
import com.example.commons.DomainComponent;
import com.example.commons.commandhandler.CommandHandler;
import com.example.paymentservice.payment.command.PaymentCreate;
import com.example.paymentservice.payment.event.PaymentCreatedEvent;
import com.example.paymentservice.payment.model.Payment;
import com.example.paymentservice.payment.port.PaymentCreatedEventPort;
import com.example.paymentservice.payment.port.PaymentPort;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class PaymentCreateCommandHandler implements CommandHandler<PaymentCreate, Payment> {

    private final CommandHandler<BalanceRetrieve, Balance> balanceRetrieveCommandHandler;
    private final CommandHandler<BalanceTransactionCreate, Balance> balanceTransactionCreateCommandHandler;
    private final PaymentCreatedEventPort paymentNotificationPort;
    private final PaymentPort paymentPort;

    @Override
    @Transactional
    public Payment handle(PaymentCreate paymentCreate) {
        BalanceTransactionCreate balanceTransactionCreate = buildBalanceTransactionCreate(paymentCreate);
        Balance balance = balanceRetrieveCommandHandler.handle(BalanceRetrieve.from(paymentCreate.getAccountId()));

        BalanceValidator.validate(balance,balanceTransactionCreate);

        try{

            Payment payment = paymentPort.create(paymentCreate);
            log.debug("Payment created for account: {}", payment.getAccountId());

            balanceTransactionCreateCommandHandler.handle(balanceTransactionCreate);
            log.debug("Total {} paid from {}", payment.getPrice(), payment.getAccountId());

            paymentNotificationPort.publish(PaymentCreatedEvent.from(payment));

            return payment;

        }catch (Exception e){
            log.error("Payment cannot be created due to errors.", e);

            throw new ApiBusinessException("paymentapi.payment.cannotBeCreated");
        }


    }

    private BalanceTransactionCreate buildBalanceTransactionCreate(PaymentCreate paymentCreate) {
        return BalanceTransactionCreate.builder()
                .accountId(paymentCreate.getAccountId())
                .amount(paymentCreate.getPrice())
                .type(BalanceTransactionType.WITHDRAW)
                .build();
    }
}
