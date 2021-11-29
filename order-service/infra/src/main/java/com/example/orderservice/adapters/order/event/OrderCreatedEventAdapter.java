package com.example.orderservice.adapters.order.event;

import com.example.orderservice.order.event.OrderCreatedEvent;
import com.example.orderservice.order.port.OrderCreatedEventPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "kafka.enabled",havingValue = "true")
public class OrderCreatedEventAdapter implements OrderCreatedEventPort {

    private final OrderEventKafkaStream orderEventKafkaStream;

    @Override
    public void publish(OrderCreatedEvent orderCreatedEvent) {
        log.info("Sending orderCreatedEvent {}", orderCreatedEvent);
        orderEventKafkaStream.orderCreatedOutput().send(MessageBuilder.withPayload(orderCreatedEvent).build());
    }
}
