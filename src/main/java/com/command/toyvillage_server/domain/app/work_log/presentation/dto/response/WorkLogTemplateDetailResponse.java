package com.command.toyvillage_server.domain.app.work_log.presentation.dto.response;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplate;

public record WorkLogTemplateDetailResponse(
        String templateTitle,
        String templateContent
) {
    public static WorkLogTemplateDetailResponse from(WorkLogTemplate workLogTemplate) {
        return new WorkLogTemplateDetailResponse(
                workLogTemplate.getTemplateTitle(),
                workLogTemplate.getTemplateContent()
        );
    }
}
