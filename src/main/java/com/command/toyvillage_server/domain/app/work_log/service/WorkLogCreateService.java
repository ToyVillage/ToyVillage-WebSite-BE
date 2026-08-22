package com.command.toyvillage_server.domain.app.work_log.service;

import com.command.toyvillage_server.domain.app.auth.admin.domain.AppAdmin;
import com.command.toyvillage_server.domain.app.auth.admin.domain.repository.AppAdminRepository;
import com.command.toyvillage_server.domain.app.auth.admin.exception.AppAdminNotFoundException;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.request.WorkLogCreateRequest;
import com.command.toyvillage_server.global.security.auth.AppAdminDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkLogCreateService {
    private final WorkLogRepository workLogRepository;
    private final AppAdminRepository appAdminRepository;

    @Transactional
    public void execute(WorkLogCreateRequest request) {
        AppAdminDetails details = getCurrentAppAdminDetails();
        AppAdmin appAdmin = appAdminRepository.findById(details.getId())
                .orElseThrow(() -> AppAdminNotFoundException.EXCEPTION);

        WorkLog workLog = WorkLog.create(
                appAdmin,
                request.templateTitle(),
                request.templateContent()
        );

        workLogRepository.save(workLog);
    }

    private AppAdminDetails getCurrentAppAdminDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof AppAdminDetails details)) {
            throw AppAdminNotFoundException.EXCEPTION;
        }

        return details;
    }
}
