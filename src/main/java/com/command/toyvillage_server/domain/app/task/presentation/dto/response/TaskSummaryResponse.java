package com.command.toyvillage_server.domain.app.task.presentation.dto.response;

import com.command.toyvillage_server.domain.app.task.domain.Task;
import com.command.toyvillage_server.domain.app.task.domain.TaskAssigneeType;
import com.command.toyvillage_server.domain.app.task.domain.TaskPriority;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record TaskSummaryResponse(
        Long id,
        String title,
        TaskAssigneeType assigneeType,
        Long assigneeId,
        String assigneeName,
        TaskPriority priority,
        LocalDate finishDate
) {
    public static TaskSummaryResponse from(Task task) {
        return TaskSummaryResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .assigneeType(task.getAssigneeType())
                .assigneeId(TaskAssignee.idOf(task))
                .assigneeName(TaskAssignee.nameOf(task))
                .priority(task.getPriority())
                .finishDate(task.getFinishDate())
                .build();
    }
}
