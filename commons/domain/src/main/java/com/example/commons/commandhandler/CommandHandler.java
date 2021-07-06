package com.example.commons.commandhandler;

import com.example.commons.model.Command;

public interface CommandHandler<T extends Command,R> {
    R handle(T command);
}
