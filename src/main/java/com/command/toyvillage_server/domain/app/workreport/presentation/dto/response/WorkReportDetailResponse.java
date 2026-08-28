package com.command.toyvillage_server.domain.app.workreport.presentation.dto.response;

import com.command.toyvillage_server.domain.app.workreport.domain.Status;
import com.command.toyvillage_server.domain.app.workreport.domain.WorkReport;
import com.command.toyvillage_server.domain.web.file.presentation.dto.response.FileResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record WorkReportDetailResponse(
        Long id,
        Long taskId,
        String name,
        String content,
        String note,
        List<FileResponse> files,
        Status status,
        String rejectionReason
) {
    public static WorkReportDetailResponse from(WorkReport workReport) {
        return WorkReportDetailResponse.builder()
                .id(workReport.getId())
                .taskId(workReport.getTask().getId())
                .name(workReport.getAppAdmin().getName())
                .content(workReport.getContent())
                .note(workReport.getNote())
                .files(
                        workReport.getFiles().stream()
                                .map(FileResponse::from)
                                .toList()
                )
                .status(workReport.getStatus())
                .rejectionReason(workReport.getRejectionReason())
                .build();
    }
}
