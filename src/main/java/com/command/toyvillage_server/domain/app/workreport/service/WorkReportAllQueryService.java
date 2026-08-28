package com.command.toyvillage_server.domain.app.workreport.service;

import com.command.toyvillage_server.domain.app.workreport.domain.Status;
import com.command.toyvillage_server.domain.app.workreport.domain.WorkReport;
import com.command.toyvillage_server.domain.app.workreport.domain.repository.WorkReportRepository;
import com.command.toyvillage_server.domain.app.workreport.presentation.dto.response.WorkReportAllResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkReportAllQueryService {
    private final WorkReportRepository workReportRepository;

    @Transactional(readOnly = true)
    public List<WorkReportAllResponse> execute(Status status){
        List<WorkReport> workReports = status == null
                ? workReportRepository.findAll()
                : workReportRepository.findAllByStatus(status);

        return workReports.stream()
                .map(WorkReportAllResponse::from)
                .toList();
    }
}
