package com.example.paymentservice.payment.command;

import com.example.paymentservice.common.model.Command;
import com.example.paymentservice.payment.model.Payment;
import com.example.paymentservice.payment.model.PaymentState;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class PaymentCreate implements Command {

    private Long accountId;
    private BigDecimal price;
    private String referenceCode;

}
