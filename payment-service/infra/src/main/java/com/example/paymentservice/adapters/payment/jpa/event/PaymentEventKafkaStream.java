package com.example.paymentservice.adapters.payment.jpa.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface PaymentEventKafkaStream {

    String PAYMENT_CREATED_OUTPUT = "paymentCreatedOutput";

    @Output(PAYMENT_CREATED_OUTPUT)
    MessageChannel paymentCreatedOutput();

}
