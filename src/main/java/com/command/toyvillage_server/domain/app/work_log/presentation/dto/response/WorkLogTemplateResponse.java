package com.command.toyvillage_server.domain.app.work_log.presentation.dto.response;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplateQuestion;
import lombok.Builder;

import java.util.List;

@Builder
public record WorkLogTemplateResponse(
    String questionTitle,
    List<WorkLogTemplateQuestionResponse> questionList
) {
    public static WorkLogTemplateResponse of(
        String questionTitle,
        List<WorkLogTemplateQuestionResponse> questionList
    ) {
        return WorkLogTemplateResponse.builder()
            .questionTitle(questionTitle)
            .questionList(questionList)
            .build();
    }
}
