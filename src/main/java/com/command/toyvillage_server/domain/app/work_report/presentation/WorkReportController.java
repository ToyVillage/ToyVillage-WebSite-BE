package com.command.toyvillage_server.domain.app.work_report.presentation;

import com.command.toyvillage_server.domain.app.work_report.service.WorkApproveService;
import com.command.toyvillage_server.domain.app.work_report.service.WorkRejectService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/work-report")
public class WorkReportController {

    private final WorkApproveService workApproveService;
    private final WorkRejectService workRejectService;

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
}