package com.command.toyvillage_server.domain.app.user.presentation.dto.response;

import com.command.toyvillage_server.domain.common.auth.user.domain.User;
import lombok.Builder;

@Builder
public record UserResponse(
    Long id,
    String name
) {
    public static UserResponse of(User user) {
        return UserResponse.builder()
            .name(user.getName())
            .build();
    }
}
