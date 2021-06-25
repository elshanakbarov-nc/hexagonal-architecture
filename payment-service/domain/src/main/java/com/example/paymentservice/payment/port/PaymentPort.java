package com.example.paymentservice.payment.port;

import com.example.paymentservice.payment.command.PaymentCreate;
import com.example.paymentservice.payment.model.Payment;

public interface PaymentPort {

    Payment create(PaymentCreate paymentCreate);

}
