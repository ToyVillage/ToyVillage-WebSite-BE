package com.command.toyvillage_server.domain.auth.presentation.dto.request;

public record AdminVerifyEmailCodeRequest(
        String code,
        String email
) {
}
