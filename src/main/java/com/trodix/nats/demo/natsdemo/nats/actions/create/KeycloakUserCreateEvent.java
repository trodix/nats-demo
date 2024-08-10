package com.trodix.nats.demo.natsdemo.nats.actions.create;

import io.nats.client.Message;
import org.springframework.context.ApplicationEvent;

public class KeycloakUserCreateEvent extends ApplicationEvent {

    private Message message;

    public KeycloakUserCreateEvent(Object source, Message message) {
        super(source);
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
