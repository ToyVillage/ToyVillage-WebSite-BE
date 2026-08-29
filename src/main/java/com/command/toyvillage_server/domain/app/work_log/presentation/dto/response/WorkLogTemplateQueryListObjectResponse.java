package com.command.toyvillage_server.domain.app.work_log.presentation.dto.response;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplate;
import lombok.Builder;

@Builder
public record WorkLogTemplateQueryListObjectResponse(
    Long templateId,
    String templateTitle
) {
    public static WorkLogTemplateQueryListObjectResponse from(WorkLogTemplate template) {
        return WorkLogTemplateQueryListObjectResponse.builder()
            .templateId(template.getId())
            .templateTitle(template.getTemplateTitle())
            .build();
    }
}
