package com.example.orderservice.adapters.order.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface OrderEventKafkaStream {

    String ORDER_CREATE_OUTPUT = "orderCreatedOutput";

    @Output(ORDER_CREATE_OUTPUT)
    MessageChannel orderCreatedOutput();
}
