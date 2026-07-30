package com.command.toyvillage_server.domain.app.open_time.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record OpenTimeCreateRequest(
        @NotNull(message = "운영 날짜를 입력해주세요.")
        LocalDate openDate
) {
}
