package com.command.toyvillage_server.domain.app.work_report.presentation.dto.response;

import com.command.toyvillage_server.domain.app.work_report.domain.Priority;
import com.command.toyvillage_server.domain.app.work_report.domain.Status;
import com.command.toyvillage_server.domain.app.work_report.domain.Visibility;
import com.command.toyvillage_server.domain.app.work_report.domain.WorkReport;
import com.command.toyvillage_server.domain.web.file.presentation.dto.response.FileResponse;

import java.time.LocalDate;
import java.util.List;

public record WorkReportDetailResponse(
        Long workReportId,
        Long employeeId,
        String employeeName,
        Status status,
        Priority priority,
        LocalDate finishDate,
        Visibility visibility,
        String title,
        String content,
        List<FileResponse> files
) {
    public static WorkReportDetailResponse from(WorkReport workReport) {
        return new WorkReportDetailResponse(
                workReport.getId(),
                workReport.getEmployee().getId(),
                workReport.getEmployee().getName(),
                workReport.getStatus(),
                workReport.getPriority(),
                workReport.getFinishDate(),
                workReport.getVisibility(),
                workReport.getTitle(),
                workReport.getContent(),
                workReport.getFiles().stream()
                        .map(FileResponse::from)
                        .toList()
        );
    }
}
