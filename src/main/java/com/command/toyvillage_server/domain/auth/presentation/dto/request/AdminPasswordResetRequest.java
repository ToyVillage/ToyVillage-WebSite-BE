package com.command.toyvillage_server.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AdminPasswordResetRequest(
        @NotBlank(message = "이메일은 공백으로 비워둘 수 없습니다.")
        @Email(message = "이메일을 올바른 형식으로 입력해주세요.")
        String email
) {
}
