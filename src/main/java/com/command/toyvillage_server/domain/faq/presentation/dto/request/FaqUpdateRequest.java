package com.command.toyvillage_server.domain.faq.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class FaqUpdateRequest {
    @NotBlank(message = "질문 내용를 비워둘 순 없습니다.")
    private String questionContent;

    @NotBlank(message = "질문 답변를 비워둘 순 없습니다.")
    private String questionAnswer;
}
