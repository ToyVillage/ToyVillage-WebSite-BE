package com.command.toyvillage_server.domain.app.work_report.service;

import com.command.toyvillage_server.domain.app.work_report.domain.WorkReport;
import com.command.toyvillage_server.domain.app.work_report.domain.repository.WorkReportRepository;
import com.command.toyvillage_server.domain.app.work_report.exception.WorkNotFoundException;
import com.command.toyvillage_server.domain.app.work_report.presentation.dto.request.WorkReportRequest;
import com.command.toyvillage_server.domain.web.file.domain.File;
import com.command.toyvillage_server.domain.web.file.domain.repository.FileRepository;
import com.command.toyvillage_server.domain.web.file.exception.FileNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkReportUpdateService {
    private final WorkReportRepository workReportRepository;
    private final FileRepository fileRepository;

    @Transactional
    public void execute(Long id,WorkReportRequest workReportRequest) {
        WorkReport workReport = workReportRepository.findById(id)
                .orElseThrow(() -> WorkNotFoundException.EXCEPTION);

        List<File> files = null;
        if (workReportRequest.fileKey() != null) {
            List<String> fileKeys = workReportRequest.fileKey();
            files = fileRepository.findAllByFileKeyIn(fileKeys);
            if (files.size() != fileKeys.size())
                throw FileNotFoundException.EXCEPTION;
        }

        workReport.update(workReportRequest.content(),workReportRequest.note(),files);
        workReportRepository.save(workReport);
    }
}
