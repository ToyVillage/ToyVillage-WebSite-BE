package com.command.toyvillage_server.domain.app.work_report.presentation.dto.response;

import com.command.toyvillage_server.domain.app.work_report.domain.WorkReport;
import lombok.Builder;

@Builder
public record WorkReportIdResponse(
        Long id
) {
    public static WorkReportIdResponse from(WorkReport workReport) {
        return WorkReportIdResponse.builder()
                .id(workReport.getId())
                .build();
    }
}
