package com.example.kitchenservice.ticket.port;


import com.example.commons.event.EventPublisher;
import com.example.kitchenservice.ticket.event.TicketCreatedEvent;

public interface TicketCreatedEventPort extends EventPublisher<TicketCreatedEvent> {
    void publish(TicketCreatedEvent event);
}
