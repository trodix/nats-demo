package com.trodix.nats.demo.natsdemo.nats;

import com.trodix.nats.demo.natsdemo.nats.actions.create.KeycloakUserCreateEvent;
import com.trodix.nats.demo.natsdemo.nats.actions.update.KeycloakUserUpdateEvent;
import com.trodix.nats.demo.natsdemo.nats.config.AppConfiguration;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;

import java.net.InetAddress;

public class NatsKeycloakEventSubscriber {

    public static final Logger LOGGER = LoggerFactory.getLogger(NatsKeycloakEventSubscriber.class);

    public static final String USER_CREATE_SUBJECT_TPL = "keycloak.event.admin.%s.success.user.create";
    public static final String USER_DELETE_SUBJECT_TPL = "keycloak.event.admin.%s.success.user.delete";
    public static final String USER_UPDATE_SUBJECT_TPL = "keycloak.event.admin.%s.success.user.update";

    private final Connection natsConnection;
    private final ApplicationEventPublisher eventPublisher;
    private final String realmId;

    public NatsKeycloakEventSubscriber(
            Connection natsConnection,
            ApplicationEventPublisher eventPublisher,
            String realmId
    ) {
        super();
        this.natsConnection = natsConnection;
        this.eventPublisher = eventPublisher;
        this.realmId = realmId;
    }

    @PostConstruct
    public void init() {
        sayHello();

        subscribeToUserCreated();
        subscribeToUserUpdated();
        subscribeToUserDeleted();
    }

    public String getUserCreateSubject() {
        return String.format(USER_CREATE_SUBJECT_TPL, realmId);
    }

    public String getUserUpdateSubject() {
        return String.format(USER_UPDATE_SUBJECT_TPL, realmId);
    }

    public String getUserDeleteSubject() {
        return String.format(USER_DELETE_SUBJECT_TPL, realmId);
    }

    protected void sayHello() {
        try {
            String hostname = InetAddress.getLocalHost().getHostName().replaceAll("\\.", "_");
            String helloSubject = String.format("spring.%s.hello", hostname);
            natsConnection.publish(helloSubject, "Hi there!".getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void subscribeToUserCreated() {
        Dispatcher dispatcher = natsConnection.createDispatcher(next -> {
            KeycloakUserCreateEvent event = new KeycloakUserCreateEvent(this, next);
            eventPublisher.publishEvent(event);
        });

        dispatcher.subscribe(getUserCreateSubject());
        LOGGER.info("Subscribed to user created event: {}", getUserCreateSubject());
    }

    protected void subscribeToUserDeleted() {
        Dispatcher dispatcher = natsConnection.createDispatcher(next -> {
            KeycloakUserCreateEvent event = new KeycloakUserCreateEvent(this, next);
            eventPublisher.publishEvent(event);
        });

        dispatcher.subscribe(getUserDeleteSubject());
        LOGGER.info("Subscribed to user deleted event: {}", getUserDeleteSubject());
    }

    protected void subscribeToUserUpdated() {
        Dispatcher dispatcher = natsConnection.createDispatcher(next -> {
            KeycloakUserUpdateEvent event = new KeycloakUserUpdateEvent(this, next);
            eventPublisher.publishEvent(event);
        });

        dispatcher.subscribe(getUserUpdateSubject());
        LOGGER.info("Subscribed to user updated event: {}", getUserUpdateSubject());
    }

}
