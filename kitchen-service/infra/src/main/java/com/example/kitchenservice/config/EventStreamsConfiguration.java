package com.example.kitchenservice.config;

import com.example.kitchenservice.adapters.order.event.OrderEventKafkaStream;
import com.example.kitchenservice.adapters.ticket.event.TicketEventKafkaStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(value = {
        OrderEventKafkaStream.class,
        TicketEventKafkaStream.class
})
public class EventStreamsConfiguration {
}
