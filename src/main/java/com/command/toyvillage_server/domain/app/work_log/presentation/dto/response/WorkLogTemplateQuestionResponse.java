package com.command.toyvillage_server.domain.app.work_log.presentation.dto.response;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplateQuestion;
import com.command.toyvillage_server.domain.app.work_log.domain.enums.QuestionType;
import com.command.toyvillage_server.domain.web.file.domain.File;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record WorkLogTemplateQuestionResponse(
    Long questionId,

    @NotBlank(message = "질문내용이 공백일 순 없습니다.")
    @Size(max = 80, message = "질문 내용을 80자 미만으로 입력해주세요.")
    String question,

    @NotNull(message = "질문 타입을 선택해주세요.")
    QuestionType questionType,

    @Size(max = 80, message = "답변을 80자 미만으로 입력해주세요.")
    String shortText,

    @Size(max = 80, message = "답변을 80자 미만으로 입력해주세요.")
    String longText,

    @Size(max = 80, message = "답변을 80자 미만으로 입력해주세요.")
    String multipleChoice,

    @Size(max = 80, message = "답변을 80자 미만으로 입력해주세요.")
    String checkBox,

    @Size(max = 80, message = "답변을 80자 미만으로 입력해주세요.")
    String dropDown,

    File fileUpload
) {
    public static WorkLogTemplateQuestionResponse from(WorkLogTemplateQuestion question) {
        return WorkLogTemplateQuestionResponse.builder()
            .questionId(question.getId())
            .question(question.getQuestion())
            .questionType(question.getQuestionType())
            .shortText(question.getShortText())
            .longText(question.getLongText())
            .multipleChoice(question.getMultipleChoice())
            .checkBox(question.getCheckBox())
            .dropDown(question.getDropDown())
            .fileUpload(question.getFileUpload())
            .build();
    }
}
