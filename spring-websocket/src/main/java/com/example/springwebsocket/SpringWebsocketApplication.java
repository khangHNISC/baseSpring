package com.example.springwebsocket;

import com.example.springwebsocket.domain.HelloMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebsocketApplication.class, args);
    }

    @MessageMapping("/hello")
    @SendTo("/greetings")
    public HelloMessage greeting(HelloMessage message) throws InterruptedException {
        Thread.sleep(1000);
        return new HelloMessage("Hello " + message.getName() + " !");
    }
}
