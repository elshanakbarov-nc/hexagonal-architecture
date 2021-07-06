package com.example.paymentservice.payment.command;

import com.example.commons.model.Command;
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
