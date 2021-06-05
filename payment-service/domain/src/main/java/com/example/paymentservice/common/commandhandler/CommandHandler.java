package com.example.paymentservice.common.commandhandler;

import com.example.paymentservice.common.model.Command;

public interface CommandHandler<T extends Command,R>{
    R handle(T command);
}
