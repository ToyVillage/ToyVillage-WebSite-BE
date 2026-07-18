package com.command.toyvillage_server.domain.common.auth.presentation.dto.response;

public record AdminVerifyEmailCodeResponse(
        String token
) {
    public static AdminVerifyEmailCodeResponse of(String token) {
        return new AdminVerifyEmailCodeResponse(token);
    }
}
