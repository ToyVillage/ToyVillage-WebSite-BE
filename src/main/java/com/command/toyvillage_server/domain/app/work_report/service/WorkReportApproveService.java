package com.command.toyvillage_server.domain.app.work_report.service;

import com.command.toyvillage_server.domain.app.work_report.domain.Status;
import com.command.toyvillage_server.domain.app.work_report.domain.WorkReport;
import com.command.toyvillage_server.domain.app.work_report.domain.repository.WorkReportRepository;
import com.command.toyvillage_server.domain.app.work_report.exception.WorkReportAlreadyApprovedException;
import com.command.toyvillage_server.domain.app.work_report.exception.WorkReportNotFoundExeception;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkReportApproveService {
    private final WorkReportRepository workReportRepository;

    @Transactional
    public void execute(Long id) {
        WorkReport workReport = workReportRepository.findById(id)
                .orElseThrow(() -> WorkReportNotFoundExeception.EXCEPTION);
        if (workReport.getStatus() == Status.APPROVED) {
            throw WorkReportAlreadyApprovedException.EXCEPTION;
        }

        workReport.approve();
    }
}
