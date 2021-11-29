package com.example.kitchenservice.ticket;

import com.example.commons.DomainComponent;
import com.example.commons.commandhandler.CommandHandler;
import com.example.kitchenservice.ticket.command.TicketCreate;
import com.example.kitchenservice.ticket.event.TicketCreatedEvent;
import com.example.kitchenservice.ticket.model.Ticket;
import com.example.kitchenservice.ticket.port.TicketCreatedEventPort;
import com.example.kitchenservice.ticket.port.TicketPort;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class TicketCreateCommandHandler implements CommandHandler<TicketCreate,Ticket> {

    private final TicketPort ticketPort;
    private final TicketCreatedEventPort ticketCreatedEventPort;

    @Override
    public Ticket handle(TicketCreate command) {
        Ticket ticket = ticketPort.createTicket(command);
        ticketCreatedEventPort.publish(TicketCreatedEvent.from(ticket));
        return ticket;
    }
}
