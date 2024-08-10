package com.trodix.nats.demo.natsdemo.nats.config;

import com.trodix.nats.demo.natsdemo.nats.NatsKeycloakEventSubscriber;
import io.nats.client.Connection;
import io.nats.client.Nats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties
public class AppConfiguration {

    public static final Logger LOGGER = LoggerFactory.getLogger(AppConfiguration.class);

    @Bean
    public Connection natsConnection(NatsConfigurationProperties config) throws IOException, InterruptedException {
        Connection connection = Nats.connectReconnectOnConnect(config.getUrl());
        LOGGER.info("Connected to Nats at {}", config.getUrl());

        return connection;
    }

    @Bean
    public NatsKeycloakEventSubscriber natsKeycloakEventSubscriber(
            Connection natsConnection,
            ApplicationEventPublisher applicationEventPublisher,
            KeycloakConfigurationProperties properties
    ) {
        Assert.notNull(properties.getRealmId(), "realmId must not be null");
        return new NatsKeycloakEventSubscriber(
                natsConnection,
                applicationEventPublisher,
                properties.getRealmId()
        );
    }

}
