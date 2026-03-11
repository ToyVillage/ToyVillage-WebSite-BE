package com.command.toyvillage_server.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(
        @NotBlank(message = "비밀번호 변경 권한 토큰을 비워둘 수 없습니다.")
        String resetToken,

        @NotBlank(message = "새 비밀번호를 비워둘 순 없습니다.")
        String newPassword
) {
}
