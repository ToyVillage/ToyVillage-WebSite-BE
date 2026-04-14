package com.command.toyvillage_server.domain.popup.presentation.dto.request;

import com.command.toyvillage_server.domain.popup.domain.enums.ContentType;

import java.time.LocalDate;

public record PopUpRequest(
        String content,
        ContentType contentType,
        LocalDate expirationDate,
        Integer priority
) {
}
