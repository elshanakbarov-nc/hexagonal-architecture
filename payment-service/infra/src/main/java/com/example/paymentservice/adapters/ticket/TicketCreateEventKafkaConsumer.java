package com.example.paymentservice.adapters.ticket;

import com.example.commons.commandhandler.CommandHandler;
import com.example.paymentservice.payment.command.PaymentCreate;
import com.example.paymentservice.payment.model.Payment;
import com.example.paymentservice.ticket.event.TicketCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketCreateEventKafkaConsumer {

    private final CommandHandler<PaymentCreate, Payment> createPaymentCommandHandler;

    @StreamListener(TicketEventKafkaStream.TICKET_CREATED_INPUT)
    public void consume(@Payload TicketCreatedEvent event){
        log.info("Ticket created event received {} ", event );
        try{
            createPaymentCommandHandler.handle(PaymentCreate.fromEvent(event));
        }catch (Exception e){
            log.warn("Payment couldn't placed", e);
        }
    }

}
