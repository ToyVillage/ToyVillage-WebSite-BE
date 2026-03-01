package com.command.toyvillage_server.domain.auth.presentation.dto.request;

public record RefreshTokenRequest(
        String refreshToken
) {
    public static RefreshTokenRequest of(String refreshToken) {
        return new RefreshTokenRequest(refreshToken);
    }
}
