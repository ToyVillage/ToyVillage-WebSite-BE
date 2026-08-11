package com.command.toyvillage_server.domain.app.work_log.presentation.dto.response;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLog;

import java.time.LocalDate;

public record WorkLogListResponse(
        Long id,
        String name,
        String templateTitle,
        LocalDate writeAt
) {
    public static WorkLogListResponse from(WorkLog workLog) {
        return new WorkLogListResponse(
                workLog.getId(),
                workLog.getAppAdmin().getName(),
                workLog.getTemplateTitle(),
                workLog.getWriteAt()
        );
    }
}
