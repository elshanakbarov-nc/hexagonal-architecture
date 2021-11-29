package com.example.kitchenservice.adapters.ticket.event;

import com.example.kitchenservice.ticket.event.TicketCreatedEvent;
import com.example.kitchenservice.ticket.port.TicketCreatedEventPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true")
public class TicketCreateEventKafkaAdapter implements TicketCreatedEventPort {

    private final TicketEventKafkaStream ticketEventKafkaStream;

    @Override
    public void publish(TicketCreatedEvent event) {
        log.info("Ticket create event sent: {}", event);
        ticketEventKafkaStream.ticketCreatedOutput().send(MessageBuilder.withPayload(event).build());
    }
}
