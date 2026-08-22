package com.command.toyvillage_server.domain.app.work_log.service;

import com.command.toyvillage_server.domain.app.auth.admin.exception.AppAdminNotFoundException;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogListResponse;
import com.command.toyvillage_server.global.security.auth.AppAdminDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkLogEmployeeQueryListService {
    private final WorkLogRepository workLogRepository;

    @Transactional(readOnly = true)
    public Page<WorkLogListResponse> execute(Pageable pageable) {
        AppAdminDetails details = getCurrentAppAdminDetails();
        Pageable latestFirst = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Order.desc("writeAt"), Sort.Order.desc("id"))
        );

        return workLogRepository.findAllByAppAdmin_Id(details.getId(), latestFirst)
                .map(WorkLogListResponse::from);
    }

    private AppAdminDetails getCurrentAppAdminDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof AppAdminDetails details)) {
            throw AppAdminNotFoundException.EXCEPTION;
        }

        return details;
    }
}
