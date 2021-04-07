package com.example.base.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by khangld5 on Apr 07, 2021
 */
@ConfigurationProperties("service")
class ServiceProperties {
    /**
     * A message for the service.
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
