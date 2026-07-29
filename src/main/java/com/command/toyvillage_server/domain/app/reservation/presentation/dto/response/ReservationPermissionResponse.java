package com.command.toyvillage_server.domain.app.reservation.presentation.dto.response;

import lombok.Builder;

@Builder
public record ReservationPermissionResponse(
    Long userId,
    String name
) {
    public static ReservationPermissionResponse of(Long userId, String name){
        return ReservationPermissionResponse.builder()
            .userId(userId)
            .name(name)
            .build();
    }
}
