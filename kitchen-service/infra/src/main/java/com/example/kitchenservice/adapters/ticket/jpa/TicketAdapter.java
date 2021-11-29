package com.example.kitchenservice.adapters.ticket.jpa;

import com.example.commons.model.Status;
import com.example.kitchenservice.adapters.ticket.jpa.entity.TicketEntity;
import com.example.kitchenservice.adapters.ticket.jpa.repo.TicketJpaRepo;
import com.example.kitchenservice.ticket.command.TicketCreate;
import com.example.kitchenservice.ticket.model.Ticket;
import com.example.kitchenservice.ticket.port.TicketPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketAdapter implements TicketPort {

    private final TicketJpaRepo ticketJpaRepo;

    @Override
    public Ticket createTicket(TicketCreate ticketCreate) {
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setOrderId(ticketCreate.getOrderId());
        ticketEntity.setStatus(Status.ACTIVE);
        TicketEntity ticket = ticketJpaRepo.save(ticketEntity);
        return ticket.toModel();
    }
}
