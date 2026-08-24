package com.command.toyvillage_server.domain.app.work_log.service;

import com.command.toyvillage_server.domain.app.auth.admin.domain.AppAdmin;
import com.command.toyvillage_server.domain.app.auth.admin.domain.repository.AppAdminRepository;
import com.command.toyvillage_server.domain.app.auth.admin.exception.AppAdminNotFoundException;
import com.command.toyvillage_server.domain.app.work_log.domain.WorkLog;
import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplate;
import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogRepository;
import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogTemplateRepository;
import com.command.toyvillage_server.domain.app.work_log.exception.WorkLogTemplateNotFoundException;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.request.WorkLogWriteRequest;
import com.command.toyvillage_server.global.security.auth.AppAdminDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkLogWriteService {
    private final WorkLogRepository workLogRepository;
    private final WorkLogTemplateRepository workLogTemplateRepository;
    private final AppAdminRepository appAdminRepository;
    private final WorkLogAnswerConverter workLogAnswerConverter;

    @Transactional
    public void execute(Long workLogTemplateId, WorkLogWriteRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof AppAdminDetails appAdminDetails)) {
            throw AppAdminNotFoundException.EXCEPTION;
        }

        AppAdmin appAdmin = appAdminRepository.findById(appAdminDetails.getId())
            .orElseThrow(() -> AppAdminNotFoundException.EXCEPTION);

        WorkLogTemplate template = workLogTemplateRepository.findById(workLogTemplateId)
            .orElseThrow(() -> WorkLogTemplateNotFoundException.EXCEPTION);

        WorkLog workLog = WorkLog.create(template, appAdmin);

        workLogAnswerConverter.convert(template, request.answers())
            .forEach(workLog::addAnswer);

        workLogRepository.save(workLog);
    }
}
