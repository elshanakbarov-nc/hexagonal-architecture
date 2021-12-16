package com.example.orderservice.payment.event;

import lombok.*;

@ToString
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PaymentCreatedEvent {
    private Long paymentId;
    private Long orderId;
}
