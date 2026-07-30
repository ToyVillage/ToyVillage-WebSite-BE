package com.command.toyvillage_server.global.security.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthTokenResponse(
        @JsonProperty("access_token")
        String accessToken,

        @JsonProperty("refresh_token")
        String refreshToken
) {
    public static AuthTokenResponse from(TokenPair tokenPair) {
        return new AuthTokenResponse(tokenPair.accessToken(), tokenPair.refreshToken());
    }
}
