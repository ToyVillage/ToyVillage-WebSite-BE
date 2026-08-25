package com.command.toyvillage_server.domain.app.work.presentation.dto.response;

import com.command.toyvillage_server.domain.app.work.domain.Priority;
import com.command.toyvillage_server.domain.app.work.domain.Status;
import com.command.toyvillage_server.domain.app.work.domain.Visibility;
import com.command.toyvillage_server.domain.app.work.domain.Work;
import java.time.LocalDate;

public record WorkResponse(
        Long workId,
        Long employeeId,
        String employeeName,
        String title,
        Status status,
        Priority priority,
        LocalDate finishDate,
        Visibility visibility
) {
    public static WorkResponse from(Work work) {
        return new WorkResponse(
                work.getId(),
                work.getEmployee().getId(),
                work.getEmployee().getName(),
                work.getTitle(),
                work.getStatus(),
                work.getPriority(),
                work.getFinishDate(),
                work.getVisibility()
        );
    }
}
