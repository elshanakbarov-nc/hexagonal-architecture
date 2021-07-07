package com.example.orderservice.order.port;

import com.example.orderservice.order.command.OrderCreate;
import com.example.orderservice.order.model.Order;

public interface OrderPort {
    Order createOrder(OrderCreate createOrder);
}
