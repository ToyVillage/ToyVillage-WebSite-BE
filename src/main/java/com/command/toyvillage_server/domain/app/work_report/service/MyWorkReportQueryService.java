package com.command.toyvillage_server.domain.app.work_report.service;

import com.command.toyvillage_server.domain.app.auth.admin.facade.UserFacade;
import com.command.toyvillage_server.domain.app.task.domain.repository.TaskRepository;
import com.command.toyvillage_server.domain.app.work_report.domain.WorkReport;
import com.command.toyvillage_server.domain.app.work_report.domain.repository.WorkReportRepository;
import com.command.toyvillage_server.domain.app.work_report.exception.WorkNotFoundException;
import com.command.toyvillage_server.domain.app.work_report.presentation.dto.response.WorkReportIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyWorkReportQueryService {
    private final WorkReportRepository workReportRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public WorkReportIdResponse execute(){
        Long currentUserId = userFacade.getCurrentUserId();

        WorkReport workReport = workReportRepository.findById(currentUserId)
                .orElseThrow(() -> WorkNotFoundException.EXCEPTION);

        return WorkReportIdResponse.from(workReport);
    }
}
