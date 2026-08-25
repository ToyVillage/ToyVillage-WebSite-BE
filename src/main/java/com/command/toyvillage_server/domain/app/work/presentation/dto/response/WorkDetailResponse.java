package com.command.toyvillage_server.domain.app.work.presentation.dto.response;

import com.command.toyvillage_server.domain.app.work.domain.Priority;
import com.command.toyvillage_server.domain.app.work.domain.Status;
import com.command.toyvillage_server.domain.app.work.domain.Visibility;
import com.command.toyvillage_server.domain.app.work.domain.Work;
import com.command.toyvillage_server.domain.web.file.presentation.dto.response.FileResponse;

import java.time.LocalDate;
import java.util.List;

public record WorkDetailResponse(
        Long workId,
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
    public static WorkDetailResponse from(Work work) {
        return new WorkDetailResponse(
                work.getId(),
                work.getEmployee().getId(),
                work.getEmployee().getName(),
                work.getStatus(),
                work.getPriority(),
                work.getFinishDate(),
                work.getVisibility(),
                work.getTitle(),
                work.getContent(),
                work.getFiles().stream()
                        .map(FileResponse::from)
                        .toList()
        );
    }
}
