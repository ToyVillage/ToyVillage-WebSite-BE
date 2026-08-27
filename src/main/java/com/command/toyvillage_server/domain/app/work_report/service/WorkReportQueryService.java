package com.command.toyvillage_server.domain.app.work_report.service;

import com.command.toyvillage_server.domain.app.auth.admin.facade.UserFacade;
import com.command.toyvillage_server.domain.app.work_report.domain.WorkReport;
import com.command.toyvillage_server.domain.app.work_report.domain.repository.WorkReportRepository;
import com.command.toyvillage_server.domain.app.work_report.exception.WorkNotFoundException;
import com.command.toyvillage_server.domain.app.work_report.presentation.dto.response.WorkReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkReportQueryService {
    private final WorkReportRepository workReportRepository;
    private final UserFacade userFacade;

    public WorkReportResponse execute(Long taskId) {
        Long currentUserId = userFacade.getCurrentUserId();

        WorkReport workReport = workReportRepository.findByTask_IdAndTask_Assignee_Id(taskId,currentUserId)
                .orElseThrow(() -> WorkNotFoundException.EXCEPTION);
        return WorkReportResponse.from(workReport);
    }
}
