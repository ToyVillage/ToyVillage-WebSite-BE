package com.command.toyvillage_server.domain.app.work_log.service;

import com.command.toyvillage_server.domain.app.auth.admin.domain.AppAdminRole;
import com.command.toyvillage_server.domain.app.auth.admin.exception.AppAdminNotFoundException;
import com.command.toyvillage_server.domain.app.work_log.domain.WorkLog;
import com.command.toyvillage_server.domain.app.work_log.domain.repository.WorkLogRepository;
import com.command.toyvillage_server.domain.app.work_log.exception.WorkLogNotFoundException;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogDetailResponse;
import com.command.toyvillage_server.global.security.auth.AppAdminDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkLogQueryService {
    private final WorkLogRepository workLogRepository;

    @Transactional(readOnly = true)
    public WorkLogDetailResponse execute(Long workLogId) {
        AppAdminDetails details = getCurrentAppAdminDetails();
        WorkLog workLog = workLogRepository.findById(workLogId)
                .orElseThrow(() -> WorkLogNotFoundException.EXCEPTION);

        if (details.appAdmin().getRole() == AppAdminRole.EMPLOYEE
                && !workLog.getAppAdmin().getId().equals(details.getId())) {
            throw WorkLogNotFoundException.EXCEPTION;
        }

        return WorkLogDetailResponse.from(workLog);
    }

    private AppAdminDetails getCurrentAppAdminDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof AppAdminDetails details)) {
            throw AppAdminNotFoundException.EXCEPTION;
        }

        return details;
    }
}
