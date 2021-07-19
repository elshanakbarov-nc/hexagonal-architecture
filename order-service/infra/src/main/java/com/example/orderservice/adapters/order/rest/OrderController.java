package com.example.orderservice.adapters.order.rest;

import com.example.commons.commandhandler.CommandHandler;
import com.example.commons.rest.BaseController;
import com.example.commons.rest.Response;
import com.example.orderservice.adapters.order.rest.dto.OrderCreateRequest;
import com.example.orderservice.adapters.order.rest.dto.OrderCreateResponse;
import com.example.orderservice.order.command.OrderCreate;
import com.example.orderservice.order.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController extends BaseController {

    private final CommandHandler<OrderCreate, Order> orderCreateCommandHandler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<OrderCreateResponse> orderCreate(@RequestBody @Valid OrderCreateRequest orderCreateRequest){
        System.out.println(orderCreateRequest.toCommand());
        var order = orderCreateCommandHandler.handle(orderCreateRequest.toCommand());
        return respond(OrderCreateResponse.fromCommand(order));
    }

}
