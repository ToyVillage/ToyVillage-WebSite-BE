package com.command.toyvillage_server.domain.common.auth.user.presentation.dto.response;

public record UserVerifyEmailCodeResponse(
        String token
) {
    public static UserVerifyEmailCodeResponse of(String token) {
        return new UserVerifyEmailCodeResponse(token);
    }
}
