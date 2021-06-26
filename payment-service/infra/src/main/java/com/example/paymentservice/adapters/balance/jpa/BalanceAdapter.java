package com.example.paymentservice.adapters.balance.jpa;

import com.example.paymentservice.adapters.balance.jpa.entity.BalanceTransactionEntity;
import com.example.paymentservice.adapters.balance.jpa.repo.BalanceTransactionJpaRepo;
import com.example.paymentservice.balance.command.BalanceTransactionCreate;
import com.example.paymentservice.balance.model.Balance;
import com.example.paymentservice.balance.port.BalancePort;
import com.example.paymentservice.common.exception.PaymentApiBusinessException;
import com.example.paymentservice.common.model.Status;
import com.example.paymentservice.adapters.balance.jpa.entity.BalanceEntity;
import com.example.paymentservice.adapters.balance.jpa.repo.BalanceJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BalanceAdapter implements BalancePort {

    private final BalanceJpaRepo balanceJpaRepo;
    private final BalanceTransactionJpaRepo balanceTransactionJpaRepo;

    @Override
    public Balance retrieve(Long accountId) {
        return balanceJpaRepo.findByAccountId(accountId)
                .orElseGet(() -> createBalance(accountId))
                .toModel();
    }

    @Override
    @Transactional
    public Balance update(Balance balance, BalanceTransactionCreate balanceTransactionCreate) {
        createBalanceTransaction(balance,balanceTransactionCreate);
        return updateBalance(balance.withAmount(balance.getAmount().add(balanceTransactionCreate.getAmountAsNumeric())));
    }

    private Balance updateBalance(Balance balance) {
        var balanceEntity = balanceJpaRepo.findByAccountId(balance.getAccountId())
                .orElseThrow(() -> new PaymentApiBusinessException("paymentapi.balance.notFound"));

        balanceEntity.setAmount(balance.getAmount());
        balanceEntity.setAccountId(balance.getAccountId());

        return balanceJpaRepo.save(balanceEntity).toModel();
    }

    private void createBalanceTransaction(Balance balance, BalanceTransactionCreate balanceTransactionCreate) {
        var balanceTransactionEntity = new BalanceTransactionEntity();
        balanceTransactionEntity.setBalanceId(balance.getId());
        balanceTransactionEntity.setType(balanceTransactionCreate.getType());
        balanceTransactionEntity.setAmount(balanceTransactionCreate.getAmount());
        balanceTransactionEntity.setStatus(Status.ACTIVE);
        balanceTransactionJpaRepo.save(balanceTransactionEntity);
    }

    private BalanceEntity createBalance(Long accountId) {
        var balanceEntity = new BalanceEntity();
        balanceEntity.setAmount(BigDecimal.ZERO);
        balanceEntity.setAccountId(accountId);
        balanceEntity.setStatus(Status.ACTIVE);
        return balanceJpaRepo.save(balanceEntity);
    }


}
