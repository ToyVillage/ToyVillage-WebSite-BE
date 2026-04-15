package com.command.toyvillage_server.domain.popup.presentation.dto.response;

import com.command.toyvillage_server.domain.popup.domain.PopUp;
import com.command.toyvillage_server.domain.popup.domain.enums.ContentType;

import java.time.LocalDate;

public record PopUpResponse(
        Long id,
        ContentType contentType,
        String content,
        LocalDate expirationDate,
        int priority
) {
    public static PopUpResponse from(PopUp popUp) {
        return new PopUpResponse(
                popUp.getId(),
                popUp.getContentType(),
                popUp.getContent(),
                popUp.getExpirationDate(),
                popUp.getPriority()
        );
    }
}
