package com.example.orderservice.adapters.order.jpa.repo;

import com.example.orderservice.adapters.order.jpa.entity.OrderEntity;
import com.example.orderservice.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepo extends JpaRepository<OrderEntity,Long> {
}
