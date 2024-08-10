package com.trodix.nats.demo.natsdemo.nats.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("app.nats")
public class NatsConfigurationProperties {

    private String url = "nats://localhost:4222";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
