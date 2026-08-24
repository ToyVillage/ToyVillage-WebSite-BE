package com.command.toyvillage_server.domain.app.work_log.service;

import com.command.toyvillage_server.domain.app.work_log.domain.MultipleChoice;
import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogSection;
import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplate;
import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplateQuestion;
import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogTemplateRepository;
import com.command.toyvillage_server.domain.app.work_log.exception.WorkLogChoiceRequiredException;
import com.command.toyvillage_server.domain.app.work_log.exception.WorkLogTemplateAlreadyExistsException;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.request.WorkLogChoiceRequest;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.request.WorkLogQuestionRequest;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.request.WorkLogTemplateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkLogTemplateCreateService {
    private final WorkLogTemplateRepository workLogTemplateRepository;

    @Transactional
    public void execute(WorkLogTemplateRequest request) {
        if (workLogTemplateRepository.existsByTemplateTitle(request.templateTitle())) {
            throw WorkLogTemplateAlreadyExistsException.EXCEPTION;
        }

        WorkLogTemplate template = WorkLogTemplate.create(request.templateTitle());

        addSections(template, request.sections());
        addQuestions(template, request.questions());

        try {
            workLogTemplateRepository.saveAndFlush(template);
        } catch (DataIntegrityViolationException exception) {
            throw WorkLogTemplateAlreadyExistsException.EXCEPTION;
        }
    }

    private void addSections(WorkLogTemplate template, List<String> sectionNames) {
        for (int order = 0; order < sectionNames.size(); order++) {
            template.addSection(WorkLogSection.create(sectionNames.get(order), order));
        }
    }

    private void addQuestions(WorkLogTemplate template, List<WorkLogQuestionRequest> questions) {
        for (int order = 0; order < questions.size(); order++) {
            WorkLogQuestionRequest questionRequest = questions.get(order);

            WorkLogTemplateQuestion question = WorkLogTemplateQuestion.create(
                questionRequest.question(),
                questionRequest.questionType(),
                order,
                questionRequest.required()
            );

            addChoices(question, questionRequest);

            template.addQuestion(question);
        }
    }

    private void addChoices(WorkLogTemplateQuestion question, WorkLogQuestionRequest request) {
        List<WorkLogChoiceRequest> choices = request.choices();

        if (request.questionType().isChoiceRequired() && choices.isEmpty()) {
            throw WorkLogChoiceRequiredException.EXCEPTION;
        }

        for (int number = 0; number < choices.size(); number++) {
            question.addChoice(MultipleChoice.create(
                number,
                choices.get(number).content(),
                choices.get(number).etc()
            ));
        }
    }
}
