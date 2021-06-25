package com.example.paymentservice.adapters.payment.rest;

import com.example.paymentservice.adapters.payment.rest.dto.PaymentCreateRequest;
import com.example.paymentservice.adapters.payment.rest.dto.PaymentResponse;
import com.example.paymentservice.common.commandhandler.CommandHandler;
import com.example.paymentservice.common.rest.BaseController;
import com.example.paymentservice.common.rest.Response;
import com.example.paymentservice.payment.command.PaymentCreate;
import com.example.paymentservice.payment.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController extends BaseController {

    private final CommandHandler<PaymentCreate, Payment> paymentCreateCommandHandler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<PaymentResponse> pay(@RequestBody @Valid PaymentCreateRequest paymentCreateRequest) {
        var payment = paymentCreateCommandHandler.handle(paymentCreateRequest.toModel());
        return respond(PaymentResponse.fromModel(payment));
    }

}
