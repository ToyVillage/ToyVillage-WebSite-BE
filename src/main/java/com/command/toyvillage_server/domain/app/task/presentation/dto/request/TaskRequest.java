package com.command.toyvillage_server.domain.app.task.presentation.dto.request;

import com.command.toyvillage_server.domain.app.task.domain.TaskAssigneeType;
import com.command.toyvillage_server.domain.app.task.domain.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record TaskRequest(
        @NotBlank(message = "업무지시 제목을 입력해주세요.")
        String title,

        @NotBlank(message = "업무지시 내용을 입력해주세요.")
        String content,

        @NotNull(message = "업무지시 대상 종류를 선택해주세요.")
        TaskAssigneeType assigneeType,

        Long assigneeId,

        @NotNull(message = "업무지시 완료기한을 선택해주세요.")
        LocalDate finishDate,

        @NotNull(message = "업무지시 우선순위를 선택해주세요.")
        TaskPriority priority,

        List<String> files
) {
}
