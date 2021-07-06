package com.example.paymentservice.adapters.balance.jpa.entity;

import com.example.commons.entity.AbstractEntity;
import com.example.paymentservice.balance.model.Balance;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Setter
@Getter
@Entity(name = "balance")
@Table(name = "balance")
@Where(clause = "status <> -1")
public class BalanceEntity extends AbstractEntity {

    private Long accountId;

    private BigDecimal amount;

    public Balance toModel() {
        return Balance.builder()
                .id(super.getId())
                .accountId(accountId)
                .amount(amount)
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .build();
    }

}
