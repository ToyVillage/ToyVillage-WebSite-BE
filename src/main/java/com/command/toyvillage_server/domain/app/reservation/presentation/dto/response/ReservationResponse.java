package com.command.toyvillage_server.domain.app.reservation.presentation.dto.response;

import com.command.toyvillage_server.domain.app.reservation.domain.Reservation;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
public record ReservationResponse(
    Long id,
    String reservationName,
    Integer leaderCount,
    Integer reservationCount,
    String location,
    LocalDateTime visitDate,
    LocalTime exitTime,
    LocalDateTime visitSiteDate,
    LocalTime visitTime,
    LocalTime visitSiteExitTime,
    Integer visitSiteCount
) {
    public static ReservationResponse from(Reservation reservation) {
        return ReservationResponse.builder()
            .id(reservation.getId())
            .reservationName(reservation.getReservationName())
            .leaderCount(reservation.getLeaderCount())
            .reservationCount(reservation.getReservationCount())
            .location(reservation.getLocation())
            .visitDate(reservation.getVisitDate())
            .exitTime(reservation.getExitTime())
            .visitSiteDate(reservation.getVisitSiteDate())
            .visitTime(reservation.getVisitSiteTime())
            .visitSiteExitTime(reservation.getVisitSiteExitTime())
            .visitSiteCount(reservation.getVisitSiteCount())
            .build();
    }
}
