package com.command.toyvillage_server.domain.app.reservation.presentation.dto.response;

import lombok.Builder;

@Builder
public record ReservationPermissionResponse(
    Long appAccountId,
    String name
) {
    public static ReservationPermissionResponse of(Long appAccountId, String name){
        return ReservationPermissionResponse.builder()
            .appAccountId(appAccountId)
            .name(name)
            .build();
    }
}
