package com.command.toyvillage_server.domain.app.work_report.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record WorkReportRequest(
        @NotBlank
        @Size(min = 1, max = 2000)
        String content,

        @Size(min = 1, max = 2000)
        String note,

        List<String> fileKey
) {
}
