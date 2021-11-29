package com.example.kitchenservice.ticket.event;

import com.example.commons.model.Event;
import com.example.kitchenservice.ticket.command.TicketCreate;
import com.example.kitchenservice.ticket.model.Ticket;
import lombok.*;

@ToString
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TicketCreatedEvent implements Event {
    private Long id;
    private Long orderId;

    public static TicketCreatedEvent from(Ticket ticket){
        return TicketCreatedEvent.builder()
                .id(ticket.getId())
                .orderId(ticket.getOrderId())
                .build();
    }
}
