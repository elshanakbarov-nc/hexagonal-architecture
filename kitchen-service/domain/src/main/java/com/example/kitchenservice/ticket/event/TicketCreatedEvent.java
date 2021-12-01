package com.example.kitchenservice.ticket.event;

import com.example.commons.model.Event;
import com.example.kitchenservice.ticket.command.TicketCreate;
import com.example.kitchenservice.ticket.model.Ticket;
import lombok.*;

import java.math.BigDecimal;

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
    private Long accountId;
    private BigDecimal price;
    private Long restaurantId;
    private String referenceCode;

    public static TicketCreatedEvent from(TicketCreate command, Ticket ticket){
        return TicketCreatedEvent.builder()
                .id(ticket.getId())
                .orderId(command.getOrderId())
                .accountId(command.getAccountId())
                .price(command.getPrice())
                .restaurantId(command.getRestaurantId())
                .referenceCode(command.getReferenceCode())
                .build();
    }
}
