package com.command.toyvillage_server.domain.app.work_log.service;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogAnswer;
import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogSection;
import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplate;
import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplateQuestion;
import com.command.toyvillage_server.domain.app.work_log.exception.WorkLogAnswerRequiredException;
import com.command.toyvillage_server.domain.app.work_log.exception.WorkLogQuestionNotFoundException;
import com.command.toyvillage_server.domain.app.work_log.exception.WorkLogSectionNotFoundException;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.request.WorkLogAnswerRequest;
import com.command.toyvillage_server.domain.web.file.domain.File;
import com.command.toyvillage_server.domain.web.file.domain.repository.FileRepository;
import com.command.toyvillage_server.domain.web.file.exception.FileNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WorkLogAnswerConverter {
    private final FileRepository fileRepository;

    public List<WorkLogAnswer> convert(WorkLogTemplate template, List<WorkLogAnswerRequest> requests) {
        Map<Long, WorkLogSection> sections = template.getSections().stream()
            .collect(Collectors.toMap(WorkLogSection::getId, Function.identity()));

        Map<Long, WorkLogTemplateQuestion> questions = template.getQuestions().stream()
            .collect(Collectors.toMap(WorkLogTemplateQuestion::getId, Function.identity()));

        List<WorkLogAnswer> answers = requests.stream()
            .map(request -> toAnswer(request, sections, questions))
            .toList();

        validateRequired(template, requests, sections);

        return answers;
    }

    private WorkLogAnswer toAnswer(
        WorkLogAnswerRequest request,
        Map<Long, WorkLogSection> sections,
        Map<Long, WorkLogTemplateQuestion> questions
    ) {
        WorkLogSection section = sections.get(request.sectionId());
        if (section == null) {
            throw WorkLogSectionNotFoundException.EXCEPTION;
        }

        WorkLogTemplateQuestion question = questions.get(request.questionId());
        if (question == null) {
            throw WorkLogQuestionNotFoundException.EXCEPTION;
        }

        return WorkLogAnswer.create(section, question, request.answerText(), findFile(request.fileId()));
    }

    private File findFile(Long fileId) {
        if (fileId == null) {
            return null;
        }

        return fileRepository.findById(fileId)
            .orElseThrow(() -> FileNotFoundException.EXCEPTION);
    }

    private void validateRequired(
        WorkLogTemplate template,
        List<WorkLogAnswerRequest> requests,
        Map<Long, WorkLogSection> sections
    ) {
        Set<String> filled = requests.stream()
            .filter(this::isFilled)
            .map(request -> key(request.sectionId(), request.questionId()))
            .collect(Collectors.toCollection(HashSet::new));

        template.getQuestions().stream()
            .filter(WorkLogTemplateQuestion::isRequired)
            .forEach(question -> sections.keySet().forEach(sectionId -> {
                if (!filled.contains(key(sectionId, question.getId()))) {
                    throw WorkLogAnswerRequiredException.EXCEPTION;
                }
            }));
    }

    private boolean isFilled(WorkLogAnswerRequest request) {
        return request.fileId() != null
            || (request.answerText() != null && !request.answerText().isBlank());
    }

    private String key(Long sectionId, Long questionId) {
        return sectionId + ":" + questionId;
    }
}
