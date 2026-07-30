package com.command.toyvillage_server.global.security.jwt;

public record TokenPair(
        String accessToken,
        String refreshToken
) {
    public static TokenPair of(String accessToken, String refreshToken) {
        return new TokenPair(accessToken, refreshToken);
    }
}
