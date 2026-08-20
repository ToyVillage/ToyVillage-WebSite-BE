package com.command.toyvillage_server.domain.app.work_report.presentation.dto.response;

import com.command.toyvillage_server.domain.app.work_report.domain.Priority;
import com.command.toyvillage_server.domain.app.work_report.domain.Status;
import com.command.toyvillage_server.domain.app.work_report.domain.Visibility;
import com.command.toyvillage_server.domain.app.work_report.domain.WorkReport;

import java.time.LocalDate;

public record WorkReportResponse(
        Long workReportId,
        String employeeId,
        String employeeName,
        String title,
        Status status,
        Priority priority,
        LocalDate finishDate,
        Visibility visibility
) {
    public static WorkReportResponse from(WorkReport workReport) {
        return new WorkReportResponse(
                workReport.getId(),
                workReport.getEmployee().getUsername(),
                workReport.getEmployee().getName(),
                workReport.getTitle(),
                workReport.getStatus(),
                workReport.getPriority(),
                workReport.getFinishDate(),
                workReport.getVisibility()
        );
    }
}
