package com.command.toyvillage_server.domain.faq.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FaqDetailResponse {
    private Long questionId;

    private String questionContent;

    private String questionAnswer;
}
