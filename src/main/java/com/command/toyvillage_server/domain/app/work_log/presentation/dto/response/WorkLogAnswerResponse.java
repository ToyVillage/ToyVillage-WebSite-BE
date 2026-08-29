package com.command.toyvillage_server.domain.app.work_log.presentation.dto.response;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogAnswer;
import com.command.toyvillage_server.domain.app.work_log.domain.enums.QuestionType;
import com.command.toyvillage_server.domain.web.file.presentation.dto.response.FileResponse;
import lombok.Builder;

@Builder
public record WorkLogAnswerResponse(
    Long questionId,
    String question,
    QuestionType questionType,
    String answerText,
    FileResponse file
) {
    public static WorkLogAnswerResponse from(WorkLogAnswer answer) {
        return WorkLogAnswerResponse.builder()
            .questionId(answer.getQuestion().getId())
            .question(answer.getQuestion().getQuestion())
            .questionType(answer.getQuestion().getQuestionType())
            .answerText(answer.getAnswerText())
            .file(answer.getFile() == null ? null : FileResponse.from(answer.getFile()))
            .build();
    }
}
