package com.command.toyvillage_server.domain.app.work_log.service;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplate;
import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplateQuestion;
import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogTemplateQuestionRepository;
import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogTemplateRepository;
import com.command.toyvillage_server.domain.app.work_log.exception.WorkLogTemplateNotFoundException;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogTemplateQuestionResponse;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogTemplateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkLogTemplateQueryOneService {
    private final WorkLogTemplateQuestionRepository workLogTemplateQuestionRepository;
    private final WorkLogTemplateRepository workLogTemplateRepository;

    @Transactional(readOnly = true)
    public WorkLogTemplateResponse execute(Long templateId) {
        WorkLogTemplate workLogTemplate = workLogTemplateRepository.findById(templateId)
            .orElseThrow(() -> WorkLogTemplateNotFoundException.EXCEPTION);

        List<WorkLogTemplateQuestion> workLogTemplateQuestions = workLogTemplateQuestionRepository.findByWorkLogTemplateId(workLogTemplate.getId());

        List<WorkLogTemplateQuestionResponse> workLogTemplateQuestionResponse = workLogTemplateQuestions.stream()
            .map(WorkLogTemplateQuestionResponse::from)
            .toList();

        return WorkLogTemplateResponse.of(
            workLogTemplate.getTemplateTitle(),
            workLogTemplateQuestionResponse
        );
    }
}
