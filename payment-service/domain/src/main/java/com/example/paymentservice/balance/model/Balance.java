package com.example.paymentservice.balance.model;

import com.example.paymentservice.balance.BalanceRetrieveCommandHandler;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@With
@ToString
@EqualsAndHashCode
public class Balance {

    private Long id;
    private Long accountId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
