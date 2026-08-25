package com.command.toyvillage_server.domain.app.work_report.service;

import com.command.toyvillage_server.domain.app.work_report.domain.Status;
import com.command.toyvillage_server.domain.app.work_report.domain.WorkReport;
import com.command.toyvillage_server.domain.app.work_report.domain.repository.WorkReportRepository;
import com.command.toyvillage_server.domain.app.work_report.exception.WorkAlreadyRejectedException;
import com.command.toyvillage_server.domain.app.work_report.exception.WorkNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkRejectService {
    private final WorkReportRepository workReportRepository;

    @Transactional
    public void execute(Long id) {
        WorkReport workReport = workReportRepository.findById(id)
                .orElseThrow(() -> WorkNotFoundException.EXCEPTION);

        if (workReport.getStatus() == Status.REJECTED) {
            throw WorkAlreadyRejectedException.EXCEPTION;
        }

        workReport.reject();
    }
}
