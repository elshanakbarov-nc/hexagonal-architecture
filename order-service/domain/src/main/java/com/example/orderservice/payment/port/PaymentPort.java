package com.example.orderservice.payment.port;

import com.example.orderservice.payment.command.PaymentCreate;
import com.example.orderservice.payment.model.Payment;

public interface PaymentPort {
    public Payment pay(PaymentCreate paymentCreate);
}
