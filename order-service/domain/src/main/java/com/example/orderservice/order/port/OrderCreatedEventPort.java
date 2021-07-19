package com.example.orderservice.order.port;

import com.example.commons.event.EventPublisher;
import com.example.orderservice.order.event.OrderCreatedEvent;

public interface OrderCreatedEventPort extends EventPublisher<OrderCreatedEvent> {
    void publish(OrderCreatedEvent event);
}
