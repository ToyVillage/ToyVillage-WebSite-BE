package com.command.toyvillage_server.domain.app.reservation.presentation.dto.response;

public record ReservationAdminQueryListResponse(
    int beforeVisitSite,
    int doneVisitSite,
    int doneVisit,

    ) {
}
