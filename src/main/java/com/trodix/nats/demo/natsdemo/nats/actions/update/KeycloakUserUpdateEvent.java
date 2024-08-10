package com.trodix.nats.demo.natsdemo.nats.actions.update;

import io.nats.client.Message;
import org.springframework.context.ApplicationEvent;

public class KeycloakUserUpdateEvent extends ApplicationEvent {

    private Message message;

    public KeycloakUserUpdateEvent(Object source, Message message) {
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
