package com.example.base.service;

import com.example.base.common.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "reservationServer",
        url = "${feign.client.url.TestUrl}",
        configuration = FeignConfiguration.class,
        fallback = FallBack.class)
public interface ReservationClient {

    @GetMapping("/reservations")
    Reservation findOne();

    @GetMapping("/booked")
    Reservation getBookedReservation();
}

@Component
class FallBack {
    public Reservation getBookedReservation() {
        Reservation reservation = new Reservation();
        reservation.setName("Jane");
        return reservation;
    }
}