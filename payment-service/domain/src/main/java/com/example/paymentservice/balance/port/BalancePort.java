package com.example.paymentservice.balance.port;

import com.example.paymentservice.balance.model.Balance;

public interface BalancePort {
    Balance retrieve(Long accountId);
}
