package com.command.toyvillage_server.domain.app.reservation.presentation.dto.response;

import com.command.toyvillage_server.domain.app.reservation.domain.Reservation;
import com.command.toyvillage_server.domain.app.reservation.domain.ReservationStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record ReservationAdminQueryResponse(
    LocalDate counselDate,
    LocalDate visitDate,
    LocalTime visitTime,
    LocalTime exitTime,
    String reservationName,
    int reservationCount,
    String location,
    String title,
    int money,
    ReservationStatus status,
    int leaderCount,
    String leaderPhoneNumber
) {
    public static ReservationAdminQueryResponse from(Reservation reservation) {
        return ReservationAdminQueryResponse.builder()
            .counselDate(reservation.getCounselDate())
            .visitDate(reservation.getVisitDate())
            .visitTime(reservation.getVisitTime())
            .exitTime(reservation.getExitTime())
            .reservationName(reservation.getReservationName())
            .reservationCount(reservation.getReservationCount())
            .location(reservation.getLocation())
            .title(reservation.getTitle())
            .money(reservation.getMoney())
            .status(reservation.getStatus())
            .leaderCount(reservation.getLeaderCount())
            .leaderPhoneNumber(reservation.getLeaderPhoneNumber())
            .build();
    }
}
