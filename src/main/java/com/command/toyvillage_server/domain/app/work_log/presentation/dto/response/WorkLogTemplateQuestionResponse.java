package com.command.toyvillage_server.domain.app.work_log.presentation.dto.response;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplateQuestion;
import com.command.toyvillage_server.domain.app.work_log.domain.enums.QuestionType;
import lombok.Builder;

import java.util.List;

@Builder
public record WorkLogTemplateQuestionResponse(
    Long questionId,
    String question,
    QuestionType questionType,
    boolean required,
    List<MultipleChoiceResponse> choices
) {
    public static WorkLogTemplateQuestionResponse from(WorkLogTemplateQuestion question) {
        return WorkLogTemplateQuestionResponse.builder()
            .questionId(question.getId())
            .question(question.getQuestion())
            .questionType(question.getQuestionType())
            .required(question.isRequired())
            .choices(question.getChoices().stream()
                .map(MultipleChoiceResponse::from)
                .toList())
            .build();
    }
}
