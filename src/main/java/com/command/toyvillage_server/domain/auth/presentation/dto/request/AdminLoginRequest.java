package com.command.toyvillage_server.domain.auth.presentation.dto.request;

public record AdminLoginRequest(
        String username,
        String password
) {
}
