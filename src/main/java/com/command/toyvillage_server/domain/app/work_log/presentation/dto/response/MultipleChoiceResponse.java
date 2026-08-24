package com.command.toyvillage_server.domain.app.work_log.presentation.dto.response;

import com.command.toyvillage_server.domain.app.work_log.domain.MultipleChoice;
import lombok.Builder;

@Builder
public record MultipleChoiceResponse(
    Long choiceId,
    Integer number,
    String content,
    boolean etc
) {
    public static MultipleChoiceResponse from(MultipleChoice choice) {
        return MultipleChoiceResponse.builder()
            .choiceId(choice.getId())
            .number(choice.getNumber())
            .content(choice.getContent())
            .etc(choice.isEtc())
            .build();
    }
}
