package com.command.toyvillage_server.domain.app.work_log.presentation.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record WorkLogTemplateQueryListResponse(
    List<String> questionTitle
) {
    public static WorkLogTemplateQueryListResponse from(List<String> questionList) {
        return WorkLogTemplateQueryListResponse.builder()
            .questionTitle(questionList)
            .build();
    }
}
