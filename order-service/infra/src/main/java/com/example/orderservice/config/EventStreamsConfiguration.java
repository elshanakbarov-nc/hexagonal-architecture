package com.example.orderservice.config;

import com.example.orderservice.adapters.order.event.OrderEventKafkaStream;
import com.example.orderservice.adapters.payment.event.PaymentEventKafkaStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(value = {
        OrderEventKafkaStream.class,
        PaymentEventKafkaStream.class
})
public class EventStreamsConfiguration {
}
