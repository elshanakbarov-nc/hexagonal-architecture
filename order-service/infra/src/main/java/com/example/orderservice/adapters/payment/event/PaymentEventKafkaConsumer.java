package com.example.orderservice.adapters.payment.event;

import com.example.orderservice.payment.event.PaymentCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentEventKafkaConsumer {

    @StreamListener(PaymentEventKafkaStream.PAYMENT_CREATE_INPUT)
    public void consume(@Payload PaymentCreatedEvent event){
        log.info("Payment created event received {} ", event );
    }


}
