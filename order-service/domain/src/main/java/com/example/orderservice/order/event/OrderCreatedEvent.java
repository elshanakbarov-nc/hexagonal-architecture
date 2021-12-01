package com.example.orderservice.order.event;

import com.example.commons.model.Event;
import com.example.orderservice.order.model.Order;
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
    private Long accountId;
    private Long restaurantId;
    private BigDecimal price;

    public static OrderCreatedEvent from( Order order){
        return OrderCreatedEvent.builder()
                .orderId(order.getId())
                .accountId(order.getAccountId())
                .restaurantId(order.getRestaurantId())
                .price(BigDecimal.valueOf(10.00))
                .build();
    }
}
