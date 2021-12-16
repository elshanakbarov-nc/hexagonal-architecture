package com.example.paymentservice.adapters.payment.jpa.event;

import com.example.paymentservice.payment.event.PaymentCreatedEvent;
import com.example.paymentservice.payment.port.PaymentCreatedEventPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "kafka.enabled",havingValue = "true")
public class PaymentCreatedEventAdapter implements PaymentCreatedEventPort {

    private final PaymentEventKafkaStream paymentEventKafkaStream;

    @Override
    public void publish(PaymentCreatedEvent event) {
        log.info("Sending paymentCreated event {} ", event);
        paymentEventKafkaStream.paymentCreatedOutput().send(MessageBuilder.withPayload(event).build());
    }
}
