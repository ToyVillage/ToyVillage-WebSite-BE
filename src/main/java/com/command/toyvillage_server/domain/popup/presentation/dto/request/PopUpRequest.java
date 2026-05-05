package com.command.toyvillage_server.domain.popup.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record PopUpRequest(

        @NotNull(message = "본문을 비워둘 순 없습니다.")
        @Size(max = 50)
        MultipartFile popupImage,

        @NotNull(message = "팝업 마감기한을 설정해주세요.")
        LocalDate expirationDate,

        @NotNull(message = "팝업의 우선순위를 선택해주세요.")
        Integer priority
) {
}
