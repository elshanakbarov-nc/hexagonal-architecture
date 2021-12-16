package com.example.orderservice.order;

import com.example.commons.DomainComponent;
import com.example.commons.commandhandler.CommandHandler;
import com.example.commons.exception.ApiBusinessException;
import com.example.orderservice.order.command.OrderCreate;
import com.example.orderservice.order.event.OrderCreatedEvent;
import com.example.orderservice.order.model.Order;
import com.example.orderservice.order.port.OrderCreatedEventPort;
import com.example.orderservice.order.port.OrderPort;
import com.example.orderservice.payment.command.PaymentCreate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class CreateOrderCommandHandler implements CommandHandler<OrderCreate,Order> {

    private final OrderPort orderPort;
    private final OrderCreatedEventPort orderNotificationPort;


    @Override
    public Order handle(OrderCreate orderCreate) {

        try{

            Order order = orderPort.createOrder(orderCreate);
            log.debug("Order with id: {} was created", order.getId());

            orderNotificationPort.publish(OrderCreatedEvent.from(order));
            log.debug("Order created event was sent for order {}", order);

            return order;

        }catch (Exception e){
            log.error("Order cannot be created due to errors.", e);

            throw new ApiBusinessException("orderapi.order.cannotBeCreated");
        }

    }

}
