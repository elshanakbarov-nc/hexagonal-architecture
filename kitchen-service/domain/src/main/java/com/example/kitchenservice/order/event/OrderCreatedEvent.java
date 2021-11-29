package com.example.kitchenservice.order.event;

import com.example.commons.model.Event;
import lombok.*;

import java.math.BigDecimal;

@ToString
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderCreatedEvent implements Event {
    private Long orderId;
    private Long paymentId;
    private Long accountId;
    private Long restaurantId;
    private BigDecimal price;
}
