package com.command.toyvillage_server.domain.auth.presentation.dto.response;

public record TokenResponse(
        String accessToken
) {
    public static TokenResponse of(String token) {
        return new TokenResponse(token);
    }
}
