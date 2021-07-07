package com.example.orderservice.adapters.order.rest.dto;

import com.example.commons.rest.ErrorResponse;
import com.example.orderservice.order.model.Order;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateResponse {
    private Long accountId;
    private Long restaurantId;
    public static OrderCreateResponse fromCommand(Order order) {
        return OrderCreateResponse.builder()
                .accountId(order.getAccountId())
                .restaurantId(order.getRestaurantId())
                .build();
    }
}
