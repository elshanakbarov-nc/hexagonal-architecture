package com.example.paymentservice.adapters.payment.jpa.repo;

import com.example.paymentservice.adapters.payment.jpa.entity.PaymentEntity;
import com.example.paymentservice.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepo extends JpaRepository<PaymentEntity,Long> {

    Payment findByAccountId(Long accountId);

}
