package com.example.kitchenservice.ticket.model;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Ticket {
    private Long id;
    private Long orderId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
