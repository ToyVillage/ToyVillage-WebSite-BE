package com.command.toyvillage_server.domain.app.work_log.presentation;

import com.command.toyvillage_server.domain.app.work_log.presentation.dto.request.WorkLogTemplateRequest;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.request.WorkLogWriteRequest;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogDetailResponse;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogListResponse;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogTemplateQueryListResponse;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogTemplateResponse;
import com.command.toyvillage_server.domain.app.work_log.service.WorkLogAdminQueryListService;
import com.command.toyvillage_server.domain.app.work_log.service.WorkLogEmployeeQueryListService;
import com.command.toyvillage_server.domain.app.work_log.service.WorkLogQueryService;
import com.command.toyvillage_server.domain.app.work_log.service.WorkLogTemplateCreateService;
import com.command.toyvillage_server.domain.app.work_log.service.WorkLogTemplateQueryListService;
import com.command.toyvillage_server.domain.app.work_log.service.WorkLogTemplateQueryService;
import com.command.toyvillage_server.domain.app.work_log.service.WorkLogUpdateService;
import com.command.toyvillage_server.domain.app.work_log.service.WorkLogWriteService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/work-log")
@RequiredArgsConstructor
public class WorkLogController {
    private final WorkLogTemplateCreateService workLogTemplateCreateService;
    private final WorkLogTemplateQueryListService workLogTemplateQueryListService;
    private final WorkLogTemplateQueryService workLogTemplateQueryService;
    private final WorkLogWriteService workLogWriteService;
    private final WorkLogUpdateService workLogUpdateService;
    private final WorkLogAdminQueryListService workLogAdminQueryListService;
    private final WorkLogEmployeeQueryListService workLogEmployeeQueryListService;
    private final WorkLogQueryService workLogQueryService;

    @PostMapping("/template")
    public ResponseEntity<MessageResponse> createWorkLogTemplate(
        @RequestBody @Valid WorkLogTemplateRequest request
    ) {
        workLogTemplateCreateService.execute(request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(MessageResponse.of("업무일지 양식 생성 성공"));
    }

    @GetMapping("/template")
    public WorkLogTemplateQueryListResponse getWorkLogTemplateList() {
        return workLogTemplateQueryListService.execute();
    }

    @GetMapping("/template/{workLogTemplateId}")
    public WorkLogTemplateResponse getWorkLogTemplateDetail(
        @PathVariable Long workLogTemplateId
    ) {
        return workLogTemplateQueryService.execute(workLogTemplateId);
    }

    @PostMapping("/employee/{workLogTemplateId}")
    public ResponseEntity<MessageResponse> writeWorkLog(
        @PathVariable Long workLogTemplateId,
        @RequestBody @Valid WorkLogWriteRequest request
    ) {
        workLogWriteService.execute(workLogTemplateId, request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(MessageResponse.of("업무일지 작성 성공"));
    }

    @PatchMapping("/employee/{workLogId}")
    public MessageResponse updateWorkLog(
        @PathVariable Long workLogId,
        @RequestBody @Valid WorkLogWriteRequest request
    ) {
        workLogUpdateService.execute(workLogId, request);

        return MessageResponse.of("업무일지 수정 성공");
    }

    @GetMapping
    public Page<WorkLogListResponse> getWorkLogList(
        @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        return workLogAdminQueryListService.execute(pageable);
    }

    @GetMapping("/employee")
    public Page<WorkLogListResponse> getEmployeeWorkLogList(
        @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        return workLogEmployeeQueryListService.execute(pageable);
    }

    @GetMapping("/{workLogId}")
    public WorkLogDetailResponse getWorkLogDetail(@PathVariable Long workLogId) {
        return workLogQueryService.execute(workLogId);
    }
}
