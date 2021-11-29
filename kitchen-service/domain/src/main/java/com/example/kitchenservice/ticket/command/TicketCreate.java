package com.example.kitchenservice.ticket.command;

import com.example.commons.model.Command;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketCreate implements Command {

    private Long orderId;

}
