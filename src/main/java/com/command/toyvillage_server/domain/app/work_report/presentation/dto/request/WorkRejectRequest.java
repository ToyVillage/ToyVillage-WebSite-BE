package com.command.toyvillage_server.domain.app.work_report.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record WorkRejectRequest(
        @NotBlank
        @Size(max = 1000)
        String rejectionReason
) {
}
