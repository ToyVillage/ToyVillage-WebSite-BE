package com.command.toyvillage_server.domain.app.work.service;

import com.command.toyvillage_server.domain.app.auth.admin.domain.AppAdminRole;
import com.command.toyvillage_server.domain.app.auth.admin.exception.AppAdminNotFoundException;
import com.command.toyvillage_server.domain.app.join_team.domain.repository.JoinTeamRepository;
import com.command.toyvillage_server.domain.app.work.domain.Visibility;
import com.command.toyvillage_server.domain.app.work.domain.Work;
import com.command.toyvillage_server.domain.app.work.domain.repository.WorkRepository;
import com.command.toyvillage_server.domain.app.work.presentation.dto.response.WorkResponse;
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
public class QueryWorkListService {

    private final WorkRepository workRepository;
    private final JoinTeamRepository joinTeamRepository;

    @Transactional(readOnly = true)
    public List<WorkResponse> execute() {
        AppAdminDetails appAdminDetails = getCurrentAppAdminDetails();

        List<Work> works = appAdminDetails.appAdmin().getRole() == AppAdminRole.APP_ADMIN
                ? workRepository.findAllByOrderByIdDesc()
                : findVisibleWorks(appAdminDetails.getId());

        return works.stream()
                .map(WorkResponse::from)
                .toList();
    }

    private List<Work> findVisibleWorks(Long appAdminId) {
        Optional<Long> teamId = joinTeamRepository.findByAppAdmin_Id(appAdminId)
                .map(joinTeam -> joinTeam.getTeam().getId());

        if (teamId.isEmpty()) {
            return workRepository.findAllByVisibilityOrderByIdDesc(Visibility.ALL);
        }

        return workRepository.findAllByVisibilityOrVisibilityAndVisibleTeam_IdOrderByIdDesc(
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
