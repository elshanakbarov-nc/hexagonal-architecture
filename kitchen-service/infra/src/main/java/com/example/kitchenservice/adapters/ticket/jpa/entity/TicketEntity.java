package com.example.kitchenservice.adapters.ticket.jpa.entity;

import com.example.commons.entity.AbstractEntity;
import com.example.kitchenservice.ticket.model.Ticket;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "tickets")
@Table(name = "ticket")
@Where(clause = "status <> -1")
public class TicketEntity extends AbstractEntity {
    @Column(nullable = false)
    private Long orderId;

    public Ticket toModel(){
        return Ticket.builder()
                .id(super.getId())
                .orderId(orderId)
                .build();
    }
}
