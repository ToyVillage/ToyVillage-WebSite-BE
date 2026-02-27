package com.command.toyvillage_server.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AdminLoginRequest(
        @NotBlank(message = "사용자 이름을 공백으로 둘 순 없습니다.")
        String username,

        @NotBlank(message = "비밀번호를 공백으로 둘 순 없습니다.")
        String password
) {
}
