package com.command.toyvillage_server.domain.app.task.presentation.dto.response;

import com.command.toyvillage_server.domain.app.task.domain.Task;
import com.command.toyvillage_server.domain.app.task.domain.TaskAssigneeType;
import com.command.toyvillage_server.domain.app.task.domain.TaskPriority;
import com.command.toyvillage_server.domain.web.file.presentation.dto.response.FileResponse;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record TaskDetailResponse(
        Long id,
        String title,
        String content,
        TaskAssigneeType assigneeType,
        Long assigneeId,
        String assigneeName,
        LocalDate finishDate,
        TaskPriority priority,
        LocalDateTime createdAt,
        List<FileResponse> files
) {
    public static TaskDetailResponse from(Task task) {
        return TaskDetailResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .content(task.getContent())
                .assigneeType(task.getAssigneeType())
                .assigneeId(task.getAssigneeId())
                .assigneeName(task.getAssigneeName())
                .finishDate(task.getFinishDate())
                .priority(task.getPriority())
                .createdAt(task.getCreatedAt())
                .files(task.getFiles().stream().map(FileResponse::from).toList())
                .build();
    }
}
