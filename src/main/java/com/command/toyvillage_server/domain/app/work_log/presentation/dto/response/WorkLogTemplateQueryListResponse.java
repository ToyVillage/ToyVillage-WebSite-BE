package com.command.toyvillage_server.domain.app.work_log.presentation.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record WorkLogTemplateQueryListResponse(
    List<WorkLogTemplateQueryListObjectResponse> templates
) {
    public static WorkLogTemplateQueryListResponse from(
        List<WorkLogTemplateQueryListObjectResponse> templates
    ) {
        return WorkLogTemplateQueryListResponse.builder()
            .templates(templates)
            .build();
    }
}
