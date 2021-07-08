package com.example.orderservice.adapters.order.jpa.entity;

import com.example.commons.entity.AbstractEntity;
import com.example.orderservice.order.model.Order;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Setter
@Getter
@Entity(name = "orders")
@Table(name = "orders")
@Where(clause = "status <> -1")
public class OrderEntity extends AbstractEntity {

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private Long restaurantId;

   public Order toModel(){
       return Order.builder()
               .id(super.getId())
               .accountId(accountId)
               .restaurantId(restaurantId)
               .build();
   }
}
