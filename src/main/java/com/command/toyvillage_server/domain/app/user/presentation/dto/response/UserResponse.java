package com.command.toyvillage_server.domain.app.user.presentation.dto.response;

import com.command.toyvillage_server.domain.app.user.domain.User;
import lombok.Builder;

@Builder
public record UserResponse(
    String name
) {
    public static UserResponse of(User user) {
        return UserResponse.builder()
            .name(user.getName())
            .build();
    }
}
