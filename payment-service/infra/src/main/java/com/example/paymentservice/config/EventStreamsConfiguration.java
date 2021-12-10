package com.example.paymentservice.config;

import com.example.paymentservice.adapters.payment.jpa.event.PaymentEventKafkaStream;
import com.example.paymentservice.adapters.ticket.TicketEventKafkaStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(value = {
        TicketEventKafkaStream.class,
        PaymentEventKafkaStream.class

})
public class EventStreamsConfiguration {
}
