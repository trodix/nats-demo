package com.trodix.nats.demo.natsdemo.nats.actions.delete;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trodix.nats.demo.natsdemo.nats.shared.KeycloakUserEventData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class KeycloakUserDeleteEventListener implements ApplicationListener<KeycloakUserDeleteEvent> {

    public static Logger LOGGER = LoggerFactory.getLogger(KeycloakUserDeleteEventListener.class);

    private final ObjectMapper objectMapper;

    public KeycloakUserDeleteEventListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onApplicationEvent(KeycloakUserDeleteEvent event) {
        String message = new String(event.getMessage().getData(), StandardCharsets.UTF_8);
        KeycloakUserEventData data;
        try {
            data = objectMapper.readValue(message, KeycloakUserEventData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("Received keycloak event [{}] - {}", event.getMessage().getSubject(), data);
    }

}
