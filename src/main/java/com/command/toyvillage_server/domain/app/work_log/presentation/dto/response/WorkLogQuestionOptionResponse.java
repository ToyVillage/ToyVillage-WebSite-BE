package com.command.toyvillage_server.domain.app.work_log.presentation.dto.response;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogQuestionOption;
import lombok.Builder;

@Builder
public record WorkLogQuestionOptionResponse(
    Long choiceId,
    Integer number,
    String content,
    boolean etcOption
) {
    public static WorkLogQuestionOptionResponse from(WorkLogQuestionOption option) {
        return WorkLogQuestionOptionResponse.builder()
            .choiceId(option.getId())
            .number(option.getNumber())
            .content(option.getContent())
            .etcOption(option.isEtcOption())
            .build();
    }
}
