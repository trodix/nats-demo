package com.trodix.nats.demo.natsdemo.nats;

import com.trodix.nats.demo.natsdemo.nats.config.KeycloakConfigurationProperties;
import io.nats.client.*;
import io.nats.client.api.ConsumerConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class NatsKeycloakEventSubscriber {

    public static final Logger LOGGER = LoggerFactory.getLogger(NatsKeycloakEventSubscriber.class);

    private final Connection natsConnection;
    private final KeycloakConfigurationProperties properties;

    public NatsKeycloakEventSubscriber(
            Connection natsConnection,
            KeycloakConfigurationProperties properties
    ) {
        this.natsConnection = natsConnection;
        this.properties = properties;

        if (properties.getRealmId() == null || properties.getRealmId().isBlank()) {
            throw new IllegalArgumentException("realmId must not be null");
        }
    }

    @PostConstruct
    public void init() {
        subscribeToUserEvents();
    }

    public String getUserCreateSubject() {
        return String.format("keycloak.event.admin.%s.success.user.create", properties.getRealmId());
    }

    public String getUserUpdateSubject() {
        return String.format("keycloak.event.admin.%s.success.user.update", properties.getRealmId());
    }

    public String getUserDeleteSubject() {
        return String.format("keycloak.event.admin.%s.success.user.delete", properties.getRealmId());
    }

    private void subscribeToUserEvents() {
        try {

            String streamName = "keycloak-admin-event-stream";
            StreamContext stream = natsConnection.jetStream().getStreamContext(streamName);

            ConsumerContext consumer = stream.createOrUpdateConsumer(ConsumerConfiguration.builder()
                            .name("spring_keycloak_consumer")
                            .durable("spring_keycloak_consumer")
                    .build()
            );

            consumer.consume(
                    message -> {
                        try {
                            NatsKeycloakEventSubscriber.this.onMessage(message);
                            message.ack();
                            LOGGER.info("Keycloak event from {} ACK", message.getSubject());
                        }  catch (Exception e) {
                            message.nak();
                            LOGGER.error("Keycloak event from {} NAK du to exception", message.getSubject(), e);
                        }
                    }
            );

        } catch (Exception e) {
            LOGGER.error("Error subscribing to user events", e);
        }
    }

    public abstract void onMessage(Message msg);

}