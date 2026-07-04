package com.command.toyvillage_server.domain.web.auth.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccessTokenResponse(
        @JsonProperty("access_token")
        String accessToken
) {
    public static AccessTokenResponse of(String accessToken) {
        return new AccessTokenResponse(accessToken);
    }
}
