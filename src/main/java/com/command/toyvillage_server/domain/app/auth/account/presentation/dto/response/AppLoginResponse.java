package com.command.toyvillage_server.domain.app.auth.account.presentation.dto.response;

import com.command.toyvillage_server.domain.app.auth.account.domain.AppAccount;
import com.command.toyvillage_server.global.security.jwt.TokenPair;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AppLoginResponse(
        @JsonProperty("access_token")
        String accessToken,

        @JsonProperty("refresh_token")
        String refreshToken,

        String name,

        String role
) {
    public static AppLoginResponse of(TokenPair tokens, AppAccount account) {
        return new AppLoginResponse(
                tokens.accessToken(),
                tokens.refreshToken(),
                account.getName(),
                account.getRole().name()
        );
    }
}
