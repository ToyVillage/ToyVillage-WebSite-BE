package com.command.toyvillage_server.domain.app.workreport.presentation;

import com.command.toyvillage_server.domain.app.workreport.presentation.dto.request.WorkRejectRequest;
import com.command.toyvillage_server.domain.app.workreport.presentation.dto.request.WorkReportRequest;
import com.command.toyvillage_server.domain.app.workreport.presentation.dto.response.WorkReportResponse;
import com.command.toyvillage_server.domain.app.workreport.service.*;
import com.command.toyvillage_server.domain.app.workreport.service.WorkApproveService;
import com.command.toyvillage_server.domain.app.workreport.service.WorkRejectService;
import com.command.toyvillage_server.domain.app.workreport.service.WorkReportCreateService;
import com.command.toyvillage_server.domain.app.workreport.service.WorkReportUpdateService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import jakarta.validation.Valid;
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
    private final WorkReportQueryService workReportQueryService;

    @PostMapping("/{id}")
    public void createWorkReport(@PathVariable Long id, @Valid @RequestBody WorkReportRequest workReportRequest) {
        workReportCreateService.execute(id,workReportRequest);
    }

    @PatchMapping("/approve/{id}")
    public MessageResponse approveWork(@PathVariable Long id) {
        workApproveService.execute(id);
        return MessageResponse.of("업무 보고가 승인되었습니다.");
    }
    @PatchMapping("/reject/{id}")
    public MessageResponse rejectWork(@PathVariable Long id,@Valid @RequestBody WorkRejectRequest workRejectRequest) {
        workRejectService.execute(id,workRejectRequest);
        return MessageResponse.of("업무 보고가 반려되었습니다.");
    }

    @PutMapping("/{id}")
    public void updateWorkReport(@PathVariable Long id, @Valid @RequestBody WorkReportRequest workReportRequest) {
        workReportUpdateService.execute(id , workReportRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkReport(@PathVariable Long id) {
        workReportDeleteService.execute(id);
    }

    @GetMapping("/{id}")
    public WorkReportResponse getWorkReport(@PathVariable Long id) {
        return workReportQueryService.execute(id);
    }
}
