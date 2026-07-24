package com.command.toyvillage_server.domain.app.team.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TeamRequest(
        @NotBlank(message = "팀 이름을 입력해주세요.")
        String name
) {
}
