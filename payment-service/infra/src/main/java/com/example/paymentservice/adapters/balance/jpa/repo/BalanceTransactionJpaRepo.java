package com.example.paymentservice.adapters.balance.jpa.repo;

import com.example.paymentservice.adapters.balance.jpa.entity.BalanceTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceTransactionJpaRepo extends JpaRepository<BalanceTransactionEntity, Long> {

}
