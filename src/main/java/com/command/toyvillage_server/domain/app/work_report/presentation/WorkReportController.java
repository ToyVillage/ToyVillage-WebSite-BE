package com.command.toyvillage_server.domain.app.work_report.presentation;

import com.command.toyvillage_server.domain.app.work_report.presentation.dto.response.WorkReportResponse;
import com.command.toyvillage_server.domain.app.work_report.service.QueryWorkReportListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/work-report")
public class WorkReportController {

    private final QueryWorkReportListService queryWorkReportListService;

    @GetMapping
    public List<WorkReportResponse> getWorkReports() {
        return queryWorkReportListService.execute();
    }
}
