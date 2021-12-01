package com.example.paymentservice.adapters.ticket;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface TicketEventKafkaStream {

    String TICKET_CREATED_INPUT = "ticketCreatedInput";

    @Input
    MessageChannel ticketCreatedInput();

}
