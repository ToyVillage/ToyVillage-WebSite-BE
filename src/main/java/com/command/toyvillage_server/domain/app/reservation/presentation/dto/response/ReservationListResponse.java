package com.command.toyvillage_server.domain.app.reservation.presentation.dto.response;

import com.command.toyvillage_server.domain.app.reservation.domain.Reservation;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record ReservationListResponse(
    Long id,
    String title,
    String reservationName,
    LocalDateTime visitDate,
    Integer reservationCount,
    LocalDate reservationDate
) {
    public static ReservationListResponse of(Reservation reservation) {
        return ReservationListResponse.builder()
            .id(reservation.getId())
            .title(reservation.getTitle())
            .reservationName(reservation.getReservationName())
            .visitDate(reservation.getVisitDate())
            .reservationCount(reservation.getReservationCount())
            .reservationDate(reservation.getReservationDate())
            .build();
    }
}
