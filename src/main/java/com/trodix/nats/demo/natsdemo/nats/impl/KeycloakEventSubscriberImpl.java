package com.trodix.nats.demo.natsdemo.nats.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trodix.nats.demo.natsdemo.nats.NatsKeycloakEventSubscriber;
import com.trodix.nats.demo.natsdemo.nats.config.KeycloakConfigurationProperties;
import io.nats.client.Connection;
import io.nats.client.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class KeycloakEventSubscriberImpl extends NatsKeycloakEventSubscriber {

    public static Logger LOGGER = LoggerFactory.getLogger(KeycloakEventSubscriberImpl.class);

    private final ObjectMapper objectMapper;

    public KeycloakEventSubscriberImpl(
            Connection natsConnection,
            KeycloakConfigurationProperties properties,
            ObjectMapper objectMapper
    ) {
        super(natsConnection, properties);
        this.objectMapper = objectMapper;
    }

    @Override
    public void onMessage(Message message) {
        String payload = new String(message.getData(), StandardCharsets.UTF_8);
        KeycloakUserEventData data;
        try {
            data = objectMapper.readValue(payload, KeycloakUserEventData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("Received keycloak event [{}] - {}", message.getSubject(), data);
    }

}
