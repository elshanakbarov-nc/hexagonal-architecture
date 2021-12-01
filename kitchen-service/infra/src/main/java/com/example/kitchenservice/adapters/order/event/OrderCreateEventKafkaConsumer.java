package com.example.kitchenservice.adapters.order.event;

import com.example.commons.commandhandler.CommandHandler;
import com.example.kitchenservice.order.event.OrderCreatedEvent;
import com.example.kitchenservice.ticket.command.TicketCreate;
import com.example.kitchenservice.ticket.model.Ticket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderCreateEventKafkaConsumer {

    private final CommandHandler<TicketCreate,Ticket> ticketCreateVoidCommandHandler;

    @StreamListener(OrderEventKafkaStream.ORDER_CREATE_INPUT)
    public void consume(@Payload OrderCreatedEvent event){
        log.info("Order create event received: {} ", event);
        try{
            ticketCreateVoidCommandHandler.handle(TicketCreate.fromEvent(event));
        }catch (Exception e){
            log.warn("Order {} is not created", event.getOrderId(), e);
        }
    }


}
