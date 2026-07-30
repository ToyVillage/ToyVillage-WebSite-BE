package com.command.toyvillage_server.domain.app.user.presentation.dto.response;

public record UserVerifyEmailCodeResponse(
        String token
) {
    public static UserVerifyEmailCodeResponse of(String token) {
        return new UserVerifyEmailCodeResponse(token);
    }
}
