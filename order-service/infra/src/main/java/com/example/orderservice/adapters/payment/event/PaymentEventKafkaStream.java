package com.example.orderservice.adapters.payment.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface PaymentEventKafkaStream {
    String PAYMENT_CREATE_INPUT = "paymentCreatedInput";

    @Input
    MessageChannel paymentCreatedInput();
}
