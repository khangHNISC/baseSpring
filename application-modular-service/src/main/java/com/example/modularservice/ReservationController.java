package com.example.modularservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    @GetMapping(value = "/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> getAllReservation() {
        return ResponseEntity.ok(new Reservation(1, "Jane"));
    }

    @Getter
    @AllArgsConstructor
    public static class Reservation {
        long id;
        String name;
    }
}
