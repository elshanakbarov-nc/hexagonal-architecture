package com.example.paymentservice.payment.event;

import com.example.commons.model.Event;
import com.example.paymentservice.payment.model.Payment;
import lombok.*;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PaymentCreatedEvent implements Event {
    private Long paymentId;
    private Long orderId;

    public static PaymentCreatedEvent from(Payment payment){
        return PaymentCreatedEvent.builder()
                .paymentId(payment.getId())
                .orderId(payment.getOrderId())
                .build();
    }
}
