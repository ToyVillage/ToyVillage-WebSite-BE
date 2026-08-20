package com.command.toyvillage_server.domain.app.work_report.service;

import com.command.toyvillage_server.domain.app.auth.admin.domain.AppAdminRole;
import com.command.toyvillage_server.domain.app.auth.admin.exception.AppAdminNotFoundException;
import com.command.toyvillage_server.domain.app.join_team.domain.repository.JoinTeamRepository;
import com.command.toyvillage_server.domain.app.work_report.domain.Visibility;
import com.command.toyvillage_server.domain.app.work_report.domain.WorkReport;
import com.command.toyvillage_server.domain.app.work_report.domain.repository.WorkReportRepository;
import com.command.toyvillage_server.domain.app.work_report.presentation.dto.response.WorkReportResponse;
import com.command.toyvillage_server.global.security.auth.AppAdminDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QueryWorkReportListService {

    private final WorkReportRepository workReportRepository;
    private final JoinTeamRepository joinTeamRepository;

    @Transactional(readOnly = true)
    public List<WorkReportResponse> execute() {
        AppAdminDetails appAdminDetails = getCurrentAppAdminDetails();

        List<WorkReport> workReports = appAdminDetails.appAdmin().getRole() == AppAdminRole.APP_ADMIN
                ? workReportRepository.findAllByOrderByIdDesc()
                : findVisibleWorkReports(appAdminDetails.getId());

        return workReports.stream()
                .map(WorkReportResponse::from)
                .toList();
    }

    private List<WorkReport> findVisibleWorkReports(Long appAdminId) {
        Optional<Long> teamId = joinTeamRepository.findByAppAdmin_Id(appAdminId)
                .map(joinTeam -> joinTeam.getTeam().getId());

        if (teamId.isEmpty()) {
            return workReportRepository.findAllByVisibilityOrderByIdDesc(Visibility.ALL);
        }

        return workReportRepository.findAllByVisibilityOrVisibilityAndVisibleTeam_IdOrderByIdDesc(
                Visibility.ALL,
                Visibility.TEAM,
                teamId.get()
        );
    }

    private AppAdminDetails getCurrentAppAdminDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof AppAdminDetails appAdminDetails)) {
            throw AppAdminNotFoundException.EXCEPTION;
        }

        return appAdminDetails;
    }
}
