package com.trodix.nats.demo.natsdemo.nats.config;

import io.nats.client.Connection;
import io.nats.client.Nats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties
public class AppConfiguration {

    public static final Logger LOGGER = LoggerFactory.getLogger(AppConfiguration.class);

    @Bean
    public Connection natsConnection(NatsConfigurationProperties config) throws IOException, InterruptedException {
        Connection connection = Nats.connect(config.getUrl());
        LOGGER.info("Connected to NATS at {}", config.getUrl());

        return connection;
    }

}