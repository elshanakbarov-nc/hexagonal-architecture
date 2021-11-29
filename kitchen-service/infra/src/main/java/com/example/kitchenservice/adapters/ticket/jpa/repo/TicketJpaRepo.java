package com.example.kitchenservice.adapters.ticket.jpa.repo;

import com.example.kitchenservice.adapters.ticket.jpa.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketJpaRepo extends JpaRepository<TicketEntity, Long>{
}
