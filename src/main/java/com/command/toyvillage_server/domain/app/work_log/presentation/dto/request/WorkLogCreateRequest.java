package com.command.toyvillage_server.domain.app.work_log.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record WorkLogCreateRequest(
        @NotBlank(message = "업무일지 제목을 비워둘 수 없습니다.")
        @Size(max = 100, message = "업무일지 제목은 100자 이하여야 합니다.")
        String templateTitle,

        @NotBlank(message = "업무일지 내용을 비워둘 수 없습니다.")
        String templateContent
) {
}
