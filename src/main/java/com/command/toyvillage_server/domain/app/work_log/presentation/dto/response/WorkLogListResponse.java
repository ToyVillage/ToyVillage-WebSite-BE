package com.command.toyvillage_server.domain.app.work_log.presentation.dto.response;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLog;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record WorkLogListResponse(
    Long workLogId,
    String templateTitle,
    String writerName,
    LocalDate writeAt
) {
    public static WorkLogListResponse from(WorkLog workLog) {
        return WorkLogListResponse.builder()
            .workLogId(workLog.getId())
            .templateTitle(workLog.getTemplate().getTemplateTitle())
            .writerName(workLog.getAppAdmin().getName())
            .writeAt(workLog.getWriteAt())
            .build();
    }
}
