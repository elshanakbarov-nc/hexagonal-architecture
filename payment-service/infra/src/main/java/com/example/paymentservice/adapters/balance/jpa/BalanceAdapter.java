package com.example.paymentservice.adapters.balance.jpa;

import com.example.paymentservice.balance.model.Balance;
import com.example.paymentservice.balance.port.BalancePort;
import com.example.paymentservice.common.model.Status;
import com.example.paymentservice.adapters.balance.jpa.entity.BalanceEntity;
import com.example.paymentservice.adapters.balance.jpa.repo.BalanceJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BalanceAdapter implements BalancePort {

    private final BalanceJpaRepo balanceJpaRepo;

    @Override
    public Balance retrieve(Long accountId) {
        return balanceJpaRepo.findByAccountId(accountId)
                .orElseGet(() -> createBalance(accountId))
                .toModel();
    }

    private BalanceEntity createBalance(Long accountId) {
        var balanceEntity = new BalanceEntity();
        balanceEntity.setAmount(BigDecimal.ZERO);
        balanceEntity.setAccountId(accountId);
        balanceEntity.setStatus(Status.ACTIVE);
        return balanceJpaRepo.save(balanceEntity);
    }
}
