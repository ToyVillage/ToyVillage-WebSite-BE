package com.command.toyvillage_server.domain.app.work_report.service;

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

    public WorkReportResponse execute(Long id) {

        WorkReport workReport = workReportRepository.findById(id)
                .orElseThrow(() -> WorkNotFoundException.EXCEPTION);

        return WorkReportResponse.from(workReport);
    }
}
