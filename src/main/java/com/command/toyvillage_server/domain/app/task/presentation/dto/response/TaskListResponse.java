package com.command.toyvillage_server.domain.app.task.presentation.dto.response;

import com.command.toyvillage_server.domain.app.task.domain.Task;
import com.command.toyvillage_server.domain.app.task.domain.TaskAssigneeType;
import com.command.toyvillage_server.domain.app.task.domain.TaskPriority;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

@Builder
public record TaskListResponse(
        List<TaskResponse> tasks,
        int totalPageSize
) {
    public static TaskListResponse from(Page<Task> tasks) {
        return TaskListResponse.builder()
                .tasks(tasks.map(TaskResponse::from).toList())
                .totalPageSize(tasks.getTotalPages())
                .build();
    }

    @Builder
    private record TaskResponse(
            Long id,
            String title,
            TaskAssigneeType assigneeType,
            Long assigneeId,
            String assigneeName,
            TaskPriority priority,
            LocalDate finishDate
    ) {
        public static TaskResponse from(Task task) {
            return TaskResponse.builder()
                    .id(task.getId())
                    .title(task.getTitle())
                    .assigneeType(task.getAssigneeType())
                    .assigneeId(task.getAssigneeId())
                    .assigneeName(task.getAssigneeName())
                    .priority(task.getPriority())
                    .finishDate(task.getFinishDate())
                    .build();
        }
    }
}
