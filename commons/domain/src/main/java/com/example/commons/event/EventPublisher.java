package com.example.commons.event;

import com.example.commons.model.Event;

public interface EventPublisher <T extends Event>{
    void publish(T event);
}
