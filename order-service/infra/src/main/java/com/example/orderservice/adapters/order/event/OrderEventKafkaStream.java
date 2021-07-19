package com.example.orderservice.adapters.order.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OrderEventKafkaStream {
    @Output
    MessageChannel orderCreatedOutput();
}
