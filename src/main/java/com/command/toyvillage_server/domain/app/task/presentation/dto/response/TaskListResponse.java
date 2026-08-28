package com.command.toyvillage_server.domain.app.task.presentation.dto.response;

import com.command.toyvillage_server.domain.app.task.domain.Task;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
public record TaskListResponse(
        List<TaskSummaryResponse> tasks,
        int totalPageSize
) {
    public static TaskListResponse from(Page<Task> tasks) {
        return TaskListResponse.builder()
                .tasks(tasks.map(TaskSummaryResponse::from).toList())
                .totalPageSize(tasks.getTotalPages())
                .build();
    }
}
