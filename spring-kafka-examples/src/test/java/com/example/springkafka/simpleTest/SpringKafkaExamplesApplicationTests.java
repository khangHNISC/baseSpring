package com.example.springkafka.simpleTest;

import com.example.springkafka.Foo1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class SpringKafkaExamplesApplicationTests {

    @Autowired
    KafkaTemplate<Object, Object> template;

    @Autowired
    KafkaConsumer consumer;

    @Test
    void checkMessage() throws InterruptedException {
        this.template.send("topic1", new Foo1("Hello khang1"));

        boolean isDone = consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        if (isDone) {
            assertFalse(consumer.getPayload().isEmpty());
        }
    }
}