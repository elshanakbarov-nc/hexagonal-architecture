package com.example.kitchenservice.ticket.port;

import com.example.kitchenservice.ticket.command.TicketCreate;
import com.example.kitchenservice.ticket.model.Ticket;

public interface TicketPort {
    Ticket createTicket(TicketCreate ticketCreate);
}
