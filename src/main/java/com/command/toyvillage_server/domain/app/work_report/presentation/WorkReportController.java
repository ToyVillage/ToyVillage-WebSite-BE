package com.command.toyvillage_server.domain.app.work_report.presentation;

import com.command.toyvillage_server.domain.app.work_report.presentation.dto.request.WorkReportRequest;
import com.command.toyvillage_server.domain.app.work_report.service.*;
import com.command.toyvillage_server.domain.app.work_report.service.WorkApproveService;
import com.command.toyvillage_server.domain.app.work_report.service.WorkRejectService;
import com.command.toyvillage_server.domain.app.work_report.service.WorkReportCreateService;
import com.command.toyvillage_server.domain.app.work_report.service.WorkReportUpdateService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/work-report")
public class WorkReportController {

    private final WorkReportCreateService workReportCreateService;
    private final WorkApproveService workApproveService;
    private final WorkRejectService workRejectService;
    private final WorkReportUpdateService workReportUpdateService;
    private final WorkReportDeleteService workReportDeleteService;

    @PostMapping("/{id}")
    public void createWorkReport(@PathVariable Long id, @RequestBody WorkReportRequest workReportRequest) {
        workReportCreateService.execute(id,workReportRequest);
    }

    @PatchMapping("/approve/{id}")
    public MessageResponse approveWork(@PathVariable Long id) {
        workApproveService.execute(id);
        return MessageResponse.of("업무 보고가 승인되었습니다.");
    }
    @PatchMapping("/reject/{id}")
    public MessageResponse rejectWork(@PathVariable Long id) {
        workRejectService.execute(id);
        return MessageResponse.of("업무 보고가 반려되었습니다.");
    }

    @PutMapping("/{id}")
    public void updateWorkReport(@PathVariable Long id, @RequestBody WorkReportRequest workReportRequest) {
        workReportUpdateService.execute(id , workReportRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkReport(@PathVariable Long id) {
        workReportDeleteService.execute(id);
    }
}