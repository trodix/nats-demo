package com.trodix.nats.demo.natsdemo.nats.actions.delete;

import io.nats.client.Message;
import org.springframework.context.ApplicationEvent;

public class KeycloakUserDeleteEvent extends ApplicationEvent {

    private Message message;

    public KeycloakUserDeleteEvent(Object source, Message message) {
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
