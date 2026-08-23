package com.command.toyvillage_server.domain.app.work_log.service;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplate;
import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogTemplateQuestionRepository;
import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogTemplateRepository;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogTemplateQueryListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkLogTemplateQueryListService {
    private final WorkLogTemplateRepository workLogTemplateRepository;
    private final WorkLogTemplateQuestionRepository workLogTemplateQuestionRepository;

    @Transactional(readOnly = true)
    public WorkLogTemplateQueryListResponse execute() {
        List<WorkLogTemplate> workLogTemplateList = workLogTemplateRepository.findAll();

        List<String> templateTitles = workLogTemplateList.stream()
            .map(WorkLogTemplate::getTemplateTitle)
            .toList();

        return WorkLogTemplateQueryListResponse.from(templateTitles);
    }
}
