package com.command.toyvillage_server.domain.app.work_log.service;

import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplate;
import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogTemplateRepository;
import com.command.toyvillage_server.domain.app.work_log.exception.WorkLogTemplateAlreadyExistsException;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.request.WorkLogTemplateCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkLogTemplateCreateService {
    private final WorkLogTemplateRepository workLogTemplateRepository;

    @Transactional
    public void execute(WorkLogTemplateCreateRequest request) {
        if (workLogTemplateRepository.existsByTemplateTitle(request.templateTitle())) {
            throw WorkLogTemplateAlreadyExistsException.EXCEPTION;
        }

        WorkLogTemplate workLogTemplate = WorkLogTemplate.create(
                request.templateTitle(),
                request.templateContent()
        );

        try {
            workLogTemplateRepository.saveAndFlush(workLogTemplate);
        } catch (DataIntegrityViolationException exception) {
            throw WorkLogTemplateAlreadyExistsException.EXCEPTION;
        }
    }
}
