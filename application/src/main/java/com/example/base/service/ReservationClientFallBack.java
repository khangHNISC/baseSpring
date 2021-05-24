package com.example.base.service;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
class ReservationClientFallBack implements FallbackFactory<ReservationClient> {

    @Override
    public ReservationClient create(Throwable cause) {
        return new ReservationClient() {
            @Override
            public Reservation findOne() {
                return null;
            }

            @Override
            public Reservation getBookedReservation() {
                Reservation reservation = new Reservation();
                reservation.setName("Jane");
                return reservation;
            }
        };
    }
}
