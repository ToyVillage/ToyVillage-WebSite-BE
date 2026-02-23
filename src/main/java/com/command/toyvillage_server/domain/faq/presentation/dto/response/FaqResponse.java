package com.command.toyvillage_server.domain.faq.presentation.dto.response;

import com.command.toyvillage_server.domain.faq.domain.Faq;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record FaqResponse(
    Long questionId,
    String questionContent,
    String questionAnswer
) {
    public static FaqResponse from(Faq faq) {
        return FaqResponse.builder()
            .questionId(faq.getId())
            .questionContent(faq.getContent())
            .questionAnswer(faq.getAnswer())
            .build();
    }
}
