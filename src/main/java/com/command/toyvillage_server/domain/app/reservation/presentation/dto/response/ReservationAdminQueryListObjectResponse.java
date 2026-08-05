package com.command.toyvillage_server.domain.app.reservation.presentation.dto.response;

import com.command.toyvillage_server.domain.app.reservation.domain.Reservation;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record ReservationAdminQueryListObjectResponse(
    LocalDate counselDate,
    LocalDate reservationDate,
    LocalTime reservationTime,
    String location,
    int count
) {
    public static ReservationAdminQueryListObjectResponse from(
        Reservation reservation
    ) {
        return ReservationAdminQueryListObjectResponse.builder()
            .counselDate(reservation.getCounselDate())
            .reservationDate(reservation.getReservationDate())
            .reservationTime(reservation.getReservationTime())
            .location(reservation.getLocation())
            .count(reservation.getReservationCount())
            .build();
    }
}
