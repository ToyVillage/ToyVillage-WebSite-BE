package com.command.toyvillage_server.domain.app.work_log.presentation.dto.response;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplate;

public record WorkLogTemplateListResponse(
        Long id,
        String templateTitle
) {
    public static WorkLogTemplateListResponse from(WorkLogTemplate workLogTemplate) {
        return new WorkLogTemplateListResponse(
                workLogTemplate.getId(),
                workLogTemplate.getTemplateTitle()
        );
    }
}
