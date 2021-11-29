package com.example.kitchenservice.adapters.ticket.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TicketEventKafkaStream {
    @Output
    MessageChannel ticketCreatedOutput();
}
