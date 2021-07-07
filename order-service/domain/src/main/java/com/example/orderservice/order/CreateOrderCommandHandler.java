package com.example.orderservice.order;

import com.example.commons.DomainComponent;
import com.example.commons.commandhandler.CommandHandler;
import com.example.orderservice.order.command.OrderCreate;
import com.example.orderservice.order.model.Order;
import com.example.orderservice.order.port.OrderPort;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class CreateOrderCommandHandler implements CommandHandler<OrderCreate,Order> {

    private final OrderPort orderPort;

    @Override
    public Order handle(OrderCreate orderCreate) {
        return orderPort.createOrder(orderCreate);
    }
}
