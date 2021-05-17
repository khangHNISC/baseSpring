package com.example.base.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "custom")
public class SomeEndPoint {

    @ReadOperation
    public String getCustomData(String name) {
        return "test " + name;
    }

    @WriteOperation
    public String setCustomData(String name) {
        return "test " + name;
    }
}
