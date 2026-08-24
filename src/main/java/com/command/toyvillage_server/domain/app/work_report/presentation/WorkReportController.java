package com.command.toyvillage_server.domain.app.work_report.presentation;

import com.command.toyvillage_server.domain.app.work_report.presentation.dto.response.WorkReportDetailResponse;
import com.command.toyvillage_server.domain.app.work_report.presentation.dto.response.WorkReportResponse;
import com.command.toyvillage_server.domain.app.work_report.service.QueryWorkReportDetailService;
import com.command.toyvillage_server.domain.app.work_report.service.QueryWorkReportListService;
import com.command.toyvillage_server.domain.app.work_report.service.WorkReportApproveService;
import com.command.toyvillage_server.domain.app.work_report.service.WorkReportRejectService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/work-report")
public class WorkReportController {

    private final QueryWorkReportListService queryWorkReportListService;
    private final QueryWorkReportDetailService queryWorkReportDetailService;
    private final WorkReportApproveService workReportApproveService;
    private final WorkReportRejectService workReportRejectService;

    @GetMapping
    public List<WorkReportResponse> getWorkReports() {
        return queryWorkReportListService.execute();
    }

    @GetMapping("/{id}")
    public WorkReportDetailResponse getWorkReportById(@PathVariable Long id) {
        return queryWorkReportDetailService.execute(id);
    }
    @PatchMapping("/approve/{id}")
    public MessageResponse approveWorkReport(@PathVariable Long id) {
        workReportApproveService.execute(id);
        return MessageResponse.of("승인에 성공했습니다.");
    }
    @PatchMapping("/reject/{id}")
    public MessageResponse rejectWorkReport(@PathVariable Long id) {
        workReportRejectService.execute(id);
        return MessageResponse.of("반려에 성공했습니다.");
    }
}
