package com.command.toyvillage_server.domain.app.reservation.presentation.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ReservationAdminQueryListResponse(
    int beforeVisitSite,
    int doneVisitSite,
    int doneVisit,
    List<ReservationAdminQueryListObjectResponse> reservationAdminQueryListObjectResponse
    ) {
    public static ReservationAdminQueryListResponse of(
        int beforeVisitSite,
        int doneVisitSite,
        int doneVisit,
        List<ReservationAdminQueryListObjectResponse> reservationAdminQueryListObjectResponse
    ) {
        return ReservationAdminQueryListResponse.builder()
            .beforeVisitSite(beforeVisitSite)
            .doneVisitSite(doneVisitSite)
            .doneVisit(doneVisit)
            .reservationAdminQueryListObjectResponse(reservationAdminQueryListObjectResponse)
            .build();
    }
}
