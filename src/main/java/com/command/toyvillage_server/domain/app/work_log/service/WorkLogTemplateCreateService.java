package com.command.toyvillage_server.domain.app.work_log.service;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplate;
import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplateQuestion;
import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogTemplateQuestionRepository;
import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogTemplateRepository;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.request.WorkLogTemplateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkLogTemplateCreateService {
    private final WorkLogTemplateRepository workLogTemplateRepository;
    private final WorkLogTemplateQuestionRepository workLogTemplateQuestionRepository;

    @Transactional
    public void execute(WorkLogTemplateRequest request) {
        WorkLogTemplate workLogTemplate = WorkLogTemplate.builder()
            .templateTitle(request.templateTitle())
            .build();

        workLogTemplateRepository.save(workLogTemplate);

        List<WorkLogTemplateQuestion> workLogTemplateQuestions = request.questions().stream()
            .map(questionRequest -> WorkLogTemplateQuestion.builder()
                .workLogTemplate(workLogTemplate)
                .question(questionRequest.question())
                .questionType(questionRequest.questionType())
                .shortText(questionRequest.shortText())
                .longText(questionRequest.longText())
                .multipleChoice(questionRequest.multipleChoice())
                .checkBox(questionRequest.checkBox())
                .dropDown(questionRequest.dropDown())
                .fileUpload(questionRequest.fileUpload())
                .build()
            )
            .toList();

        workLogTemplateQuestionRepository.saveAll(workLogTemplateQuestions);
    }
}
