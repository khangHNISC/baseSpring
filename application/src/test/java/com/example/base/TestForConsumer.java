package com.example.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureStubRunner(ids = "com.example:application-modular-service:+:8080",
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class TestForConsumer {

    TestRestTemplate template = new TestRestTemplate();

    @Test
    void getAllReservation() {
        Reservation a = template.getForObject("http://localhost:8080/reservations", Reservation.class);
        Assertions.assertNotNull(a);
        assertEquals("Jane", a.getName());
    }

    @Setter
    @Getter
    @NoArgsConstructor
    static class Reservation {
        long id;
        String name;
    }
}
