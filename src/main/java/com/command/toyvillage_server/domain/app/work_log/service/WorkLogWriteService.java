package com.command.toyvillage_server.domain.app.work_log.service;

import com.command.toyvillage_server.domain.app.auth.admin.domain.AppAdmin;
import com.command.toyvillage_server.domain.app.auth.admin.domain.repository.AppAdminRepository;
import com.command.toyvillage_server.domain.app.auth.admin.exception.AppAdminNotFoundException;
import com.command.toyvillage_server.domain.app.work_log.domain.WorkLog;
import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogAnswer;
import com.command.toyvillage_server.domain.app.work_log.domain.WorkLogTemplate;
import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogRepository;
import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogTemplateRepository;
import com.command.toyvillage_server.domain.app.work_log.exception.WorkLogTemplateNotFoundException;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.request.WorkLogWriteRequest;
import com.command.toyvillage_server.domain.web.file.domain.File;
import com.command.toyvillage_server.domain.web.file.domain.repository.FileRepository;
import com.command.toyvillage_server.domain.web.file.exception.FileNotFoundException;
import com.command.toyvillage_server.global.security.auth.AppAdminDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkLogWriteService {
    private final WorkLogRepository workLogRepository;
    private final WorkLogTemplateRepository workLogTemplateRepository;
    private final AppAdminRepository appAdminRepository;
    private final FileRepository fileRepository;

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

        List<WorkLogAnswer> answers = request.answers().stream()
            .map(answer -> template.createAnswer(
                answer.sectionId(),
                answer.questionId(),
                answer.answerText(),
                findFile(answer.fileId())
            ))
            .toList();

        template.validateRequiredAnswered(answers);

        WorkLog workLog = WorkLog.create(template, appAdmin);
        answers.forEach(workLog::addAnswer);

        workLogRepository.save(workLog);
    }

    private File findFile(Long fileId) {
        if (fileId == null) {
            return null;
        }

        return fileRepository.findById(fileId)
            .orElseThrow(() -> FileNotFoundException.EXCEPTION);
    }
}
