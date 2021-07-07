package com.example.orderservice.adapters.order.jpa;

import com.example.commons.model.Status;
import com.example.orderservice.adapters.order.jpa.entity.OrderEntity;
import com.example.orderservice.adapters.order.jpa.repo.OrderJpaRepo;
import com.example.orderservice.order.command.OrderCreate;
import com.example.orderservice.order.model.Order;
import com.example.orderservice.order.port.OrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderAdapter implements OrderPort {

    private final OrderJpaRepo orderJpaRepo;

    @Override
    public Order createOrder(OrderCreate createOrder) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setAccountId(createOrder.getAccountId());
        orderEntity.setRestaurantId(createOrder.getRestaurantId());
        orderEntity.setStatus(Status.ACTIVE);
        System.out.println(orderEntity.toString());
        OrderEntity order = orderJpaRepo.save(orderEntity);
        return toModel(order);
    }

    private Order toModel(OrderEntity order) {
        return Order.builder()
                .accountId(order.getAccountId())
                .restaurantId(order.getRestaurantId())
                .build();
    }
}
