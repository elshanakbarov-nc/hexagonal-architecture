package com.example.orderservice.order.command;

import com.example.commons.model.Command;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderCreate implements Command {
    private Long accountId;
    private Long restaurantId;

}
