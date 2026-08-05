package com.command.toyvillage_server.domain.app.reservation.presentation.dto.response;

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
    public static ReservationAdminQueryListObjectResponse of(
        LocalDate counselDate,
        LocalDate reservationDate,
        LocalTime reservationTime,
        String location,
        int count
    ) {
        return ReservationAdminQueryListObjectResponse.builder()
            .counselDate(counselDate)
            .reservationDate(reservationDate)
            .reservationTime(reservationTime)
            .location(location)
            .count(count)
            .build();
    }
}
