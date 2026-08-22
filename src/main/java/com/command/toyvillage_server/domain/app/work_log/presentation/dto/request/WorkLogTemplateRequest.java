package com.command.toyvillage_server.domain.app.work_log.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record WorkLogTemplateRequest(
        @NotBlank(message = "업무일지 템플릿 제목을 비워둘 수 없습니다.")
        @Size(max = 100, message = "업무일지 템플릿 제목은 100자 이하여야 합니다.")
        String templateTitle,

        @NotEmpty(message = "질문을 생성해주세요.")
        List<WorkLogTemplateQuestionResponse> questions
) {
}
