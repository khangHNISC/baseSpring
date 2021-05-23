package com.example.springkafka.rabbit;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;

@TestConfiguration
public class RabbitMQConsumer {

    @RabbitListener(id = "foo", queues = "spring-boot")
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        System.out.println("Received <" + message + ">");
        channel.basicAck(tag, false);
    }
}
