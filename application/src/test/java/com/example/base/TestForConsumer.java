package com.example.base;

import com.example.base.service.Reservation;
import com.example.base.service.ReservationClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = {
        "feign.circuitbreaker.enabled=true",
        "feign.client.config.default.connectTimeout=5000",
        "feign.client.config.default.readTimeout=5000"
})
@AutoConfigureStubRunner(ids = "com.example:application-modular-service:+:8080",
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class TestForConsumer {

    @Autowired
    ReservationClient client;

    TestRestTemplate template = new TestRestTemplate();

    @Test
    void getAllReservation() {
        Reservation a = template.getForObject("http://localhost:8080/reservation", Reservation.class);
        Assertions.assertNotNull(a);
        assertEquals("Jane", a.getName());
    }

    @Test
    void getAllReservationUsingFeign() {
        Reservation a = client.findOne();
        Assertions.assertNotNull(a);
        assertEquals("Jane", a.getName());
    }

    /**
     * before doing with dependency please check the correct version first !!!
     */
    @Test
    void fallback_IfServerUnResponse() {
        Reservation a = client.getBookedReservation();
        assertEquals("Jane", a.getName());
    }
}
