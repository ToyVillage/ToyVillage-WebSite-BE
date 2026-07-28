package com.command.toyvillage_server.domain.app.reservation.presentation.dto.response;

import lombok.Builder;

@Builder
public record ReservationPermissionResponse(
    String name
) {
    public static ReservationPermissionResponse of(String name){
        return ReservationPermissionResponse.builder()
            .name(name)
            .build();
    }
}
