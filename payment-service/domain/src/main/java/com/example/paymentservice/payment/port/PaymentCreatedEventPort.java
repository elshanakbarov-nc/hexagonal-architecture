package com.example.paymentservice.payment.port;

import com.example.commons.event.EventPublisher;
import com.example.paymentservice.payment.event.PaymentCreatedEvent;

public interface PaymentCreatedEventPort extends EventPublisher<PaymentCreatedEvent> {
    void publish(PaymentCreatedEvent event);
}
