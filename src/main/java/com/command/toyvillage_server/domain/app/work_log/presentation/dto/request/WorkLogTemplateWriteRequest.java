package com.command.toyvillage_server.domain.app.work_log.presentation.dto.request;

import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogTemplateQuestionResponse;

import java.util.List;

public record WorkLogTemplateWriteRequest(
    List<WorkLogTemplateQuestionResponse> questionList
) {
}
