package com.example.paymentservice.ticket.event;

import com.example.commons.model.Event;
import lombok.*;

import java.math.BigDecimal;

@ToString
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TicketCreatedEvent implements Event {
    private Long ticketId;
    private Long orderId;
    private Long accountId;
    private BigDecimal price;
    private Long restaurantId;
    private String referenceCode;
}
