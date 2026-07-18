package com.command.toyvillage_server.domain.common.auth.user.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserSignUpRequest(
        @Email(message = "올바른 이메일 형식으로 입력해주세요.")
        @NotBlank(message = "이메일을 공백으로 둘 순 없습니다.")
        String email,

        @NotBlank(message = "이름을 공백으로 둘 순 없습니다.")
        String name,

        @NotBlank(message = "비밀번호를 공백으로 둘 순 없습니다.")
        String password,

        @NotBlank(message = "이메일 인증이 필요합니다.")
        String verificationToken
) {
}
