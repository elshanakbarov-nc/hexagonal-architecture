package com.example.commons.commandhandler;

import com.example.commons.model.Command;

public interface VoidCommandHandler<T extends Command> {
    void handle(T command);
}
