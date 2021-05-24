package com.example.base.service;

import com.example.base.common.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "reservationServer",
        url = "${feign.client.url.TestUrl}",
        configuration = FeignConfiguration.class,
        fallbackFactory = ReservationClientFallBack.class)
public interface ReservationClient {

    @GetMapping("/reservation")
    Reservation findOne();

    @GetMapping("/booked")
    Reservation getBookedReservation();

}
