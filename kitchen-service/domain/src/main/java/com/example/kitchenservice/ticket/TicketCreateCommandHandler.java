package com.example.kitchenservice.ticket;

import com.example.commons.DomainComponent;
import com.example.commons.commandhandler.CommandHandler;
import com.example.commons.exception.ApiBusinessException;
import com.example.kitchenservice.ticket.command.TicketCreate;
import com.example.kitchenservice.ticket.event.TicketCreatedEvent;
import com.example.kitchenservice.ticket.model.Ticket;
import com.example.kitchenservice.ticket.port.TicketCreatedEventPort;
import com.example.kitchenservice.ticket.port.TicketPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class TicketCreateCommandHandler implements CommandHandler<TicketCreate,Ticket> {

    private final TicketPort ticketPort;
    private final TicketCreatedEventPort ticketNotificationPort;

    @Override
    public Ticket handle(TicketCreate command) {
        try{

            Ticket ticket = ticketPort.createTicket(command);
            log.debug("Ticket with id: {} was created", ticket.getId());

            ticketNotificationPort.publish(TicketCreatedEvent.from(command, ticket));
            log.debug("Ticket created event was sent for ticket {}", ticket);

            return ticket;

        }catch (Exception e){
            log.error("Ticket cannot be created due to errors.", e);

            throw new ApiBusinessException("ticketapi.ticket.cannotBeCreated");
        }

    }
}
