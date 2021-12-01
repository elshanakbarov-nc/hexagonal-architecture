package com.example.kitchenservice.ticket.command;

import com.example.commons.model.Command;
import com.example.kitchenservice.order.event.OrderCreatedEvent;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TicketCreate implements Command {

    private Long orderId;
    private Long accountId;
    private Long restaurantId;
    private BigDecimal price;
    private String referenceCode;

    public static TicketCreate fromEvent(OrderCreatedEvent event){
        return TicketCreate.builder()
                .orderId(event.getOrderId())
                .accountId(event.getAccountId())
                .restaurantId(event.getRestaurantId())
                .price(event.getPrice())
                .referenceCode(event.getReferenceCode())
                .build();
    }

}
