package com.example.paymentservice.adapters.balance.jpa.repo;

import com.example.paymentservice.adapters.balance.jpa.entity.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceJpaRepo extends JpaRepository<BalanceEntity, Long> {

    Optional<BalanceEntity> findByAccountId(Long accountId);
}
