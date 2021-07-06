package com.example.paymentservice.adapters.balance.jpa.entity;

import com.example.paymentservice.balance.model.BalanceTransaction;
import com.example.paymentservice.balance.model.BalanceTransactionType;
import com.example.commons.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "balance_transaction")
@Table(name = "balance_transaction")
@Where(clause = "status <> -1")
public class BalanceTransactionEntity extends AbstractEntity {

    @Column(nullable = false)
    private Long balanceId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BalanceTransactionType type;

    public BalanceTransaction toModel(){
        return BalanceTransaction.builder()
                .id(super.getId())
                .amount(getAmount())
                .balanceId(getBalanceId())
                .createdAt(getCreatedAt())
                .type(getType())
                .build();
    }

}
