package com.command.toyvillage_server.domain.popup.presentation.dto.request;

import com.command.toyvillage_server.domain.popup.domain.enums.ContentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PopUpRequest(

        @NotBlank(message = "본문을 비워둘 순 없습니다.")
        @Size(max = 50)
        String content,

        @NotBlank(message = "본문의 형식을 지정해주세요.")
        ContentType contentType,

        @NotBlank(message = "팝업 마감기한을 설정해주세요.")
        LocalDate expirationDate,

        @NotBlank(message = "팝업의 우선순위를 선택해주세요.")
        Integer priority
) {
}
