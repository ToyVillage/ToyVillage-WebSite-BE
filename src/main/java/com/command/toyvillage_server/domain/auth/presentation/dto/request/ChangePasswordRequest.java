package com.command.toyvillage_server.domain.auth.presentation.dto.request;

public record ChangePasswordRequest(
        String resetToken,
        String newPassword
) {
}
