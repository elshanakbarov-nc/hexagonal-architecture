package com.example.orderservice.adapters.order.rest.dto;

import com.example.orderservice.order.command.OrderCreate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {
    @NotNull
    private Long accountId;
    @NotNull
    private Long restaurantId;

    public OrderCreate toCommand() {
       return OrderCreate.builder()
                .accountId(accountId)
                .restaurantId(restaurantId)
                .build();
    }
}
