package com.command.toyvillage_server.domain.app.work_log.presentation.dto.response;

import java.time.LocalDate;

public record WorkLogDetailResponse(
        LocalDate writeAt,
        String templateTitle,
        String templateContent
) {
    public static WorkLogDetailResponse from(WorkLog workLog) {
        return new WorkLogDetailResponse(
                workLog.getWriteAt(),
                workLog.getTemplateTitle(),
                workLog.getTemplateContent()
        );
    }
}
