package com.example.springkafka.kafka;

import com.example.springkafka.Foo2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

@Slf4j
@TestConfiguration
class KafkaConsumer {

    private final CountDownLatch latch = new CountDownLatch(1);
    private String payload = null;

    @KafkaListener(id = "fooGroup",topics = "topic1")
    public void listen(Foo2 foo) {
        log.info("Received: " + foo.getFoo());
        payload = foo.getFoo();
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public String getPayload() {
        return payload;
    }
}
