package com.command.toyvillage_server.domain.app.work_report.service;

import com.command.toyvillage_server.domain.app.work_report.domain.WorkReport;
import com.command.toyvillage_server.domain.app.work_report.domain.repository.WorkReportRepository;
import com.command.toyvillage_server.domain.app.work_report.exception.WorkReportNotFoundExeception;
import com.command.toyvillage_server.domain.app.work_report.presentation.dto.response.WorkReportDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryWorkReportDetailService {

    private final WorkReportRepository workReportRepository;

    @Transactional(readOnly = true)
    public WorkReportDetailResponse execute(Long workReportId) {
        WorkReport workReport = workReportRepository.findById(workReportId)
                .orElseThrow(() -> WorkReportNotFoundExeception.EXCEPTION);

        return WorkReportDetailResponse.from(workReport);

    }

}
