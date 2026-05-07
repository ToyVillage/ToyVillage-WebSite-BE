package com.command.toyvillage_server.domain.popup.presentation.dto.response;

import com.command.toyvillage_server.domain.popup.domain.PopUp;

import java.time.LocalDate;

public record PopUpResponse(
        Long id,
        String popupImage,
        LocalDate expirationDate,
        int priority
) {
    public static PopUpResponse from(PopUp popUp) {
        return new PopUpResponse(
                popUp.getId(),
                popUp.getPopupImage(),
                popUp.getExpirationDate(),
                popUp.getPriority()
        );
    }
}
