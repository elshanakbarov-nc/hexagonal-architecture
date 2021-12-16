package com.example.kitchenservice.adapters.order.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface OrderEventKafkaStream {

    String ORDER_CREATE_INPUT = "orderCreatedInput";

    @Input
    MessageChannel orderCreatedInput();

}
