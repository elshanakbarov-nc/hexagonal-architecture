package com.example.paymentservice.payment.command;

import com.example.commons.model.Command;
import com.example.commons.model.Event;
import com.example.paymentservice.ticket.event.TicketCreatedEvent;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class PaymentCreate implements Command {

    private Long accountId;
    private Long orderId;
    private BigDecimal price;
    private String referenceCode;

    public static PaymentCreate fromEvent(TicketCreatedEvent event){
        return PaymentCreate.builder()
                .accountId(event.getAccountId())
                .orderId(event.getOrderId())
                .price(event.getPrice())
                .referenceCode("EVENT")
                .build();
    }


}
