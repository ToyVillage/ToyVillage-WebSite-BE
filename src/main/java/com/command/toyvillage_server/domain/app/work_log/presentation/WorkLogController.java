package com.command.toyvillage_server.domain.app.work_log.presentation;

import com.command.toyvillage_server.domain.app.work_log.presentation.dto.request.WorkLogTemplateRequest;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.request.WorkLogWriteRequest;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogDetailResponse;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogListResponse;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogTemplateQueryListResponse;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogTemplateResponse;
import com.command.toyvillage_server.domain.app.work_log.service.work_log.WorkLogAdminQueryListService;
import com.command.toyvillage_server.domain.app.work_log.service.work_log.WorkLogEmployeeDeleteService;
import com.command.toyvillage_server.domain.app.work_log.service.work_log.WorkLogEmployeeQueryListService;
import com.command.toyvillage_server.domain.app.work_log.service.work_log.WorkLogAdminQueryService;
import com.command.toyvillage_server.domain.app.work_log.service.template.WorkLogTemplateAdminCreateService;
import com.command.toyvillage_server.domain.app.work_log.service.template.WorkLogTemplateEmployeeQueryListService;
import com.command.toyvillage_server.domain.app.work_log.service.template.WorkLogTemplateEmployeeQueryService;
import com.command.toyvillage_server.domain.app.work_log.service.work_log.WorkLogEmployeeUpdateService;
import com.command.toyvillage_server.domain.app.work_log.service.work_log.WorkLogEmployeeWriteService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/work-log")
@RequiredArgsConstructor
public class WorkLogController {
    private final WorkLogTemplateAdminCreateService workLogTemplateAdminCreateService;
    private final WorkLogTemplateEmployeeQueryListService workLogTemplateEmployeeQueryListService;
    private final WorkLogTemplateEmployeeQueryService workLogTemplateEmployeeQueryService;
    private final WorkLogEmployeeWriteService workLogEmployeeWriteService;
    private final WorkLogEmployeeUpdateService workLogEmployeeUpdateService;
    private final WorkLogEmployeeDeleteService workLogEmployeeDeleteService;
    private final WorkLogAdminQueryListService workLogAdminQueryListService;
    private final WorkLogEmployeeQueryListService workLogEmployeeQueryListService;
    private final WorkLogAdminQueryService workLogAdminQueryService;

    @PostMapping("/template")
    public ResponseEntity<MessageResponse> createWorkLogTemplate(
        @RequestBody @Valid WorkLogTemplateRequest request
    ) {
        workLogTemplateAdminCreateService.execute(request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(MessageResponse.of("업무일지 양식 생성 성공"));
    }

    @GetMapping("/template")
    public WorkLogTemplateQueryListResponse getWorkLogTemplateList() {
        return workLogTemplateEmployeeQueryListService.execute();
    }

    @GetMapping("/template/{workLogTemplateId}")
    public WorkLogTemplateResponse getWorkLogTemplateDetail(
        @PathVariable Long workLogTemplateId
    ) {
        return workLogTemplateEmployeeQueryService.execute(workLogTemplateId);
    }

    @PostMapping("/employee/{workLogTemplateId}")
    public ResponseEntity<MessageResponse> writeWorkLog(
        @PathVariable Long workLogTemplateId,
        @RequestBody @Valid WorkLogWriteRequest request
    ) {
        workLogEmployeeWriteService.execute(workLogTemplateId, request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(MessageResponse.of("업무일지 작성 성공"));
    }

    @PatchMapping("/employee/{workLogId}")
    public MessageResponse updateWorkLog(
        @PathVariable Long workLogId,
        @RequestBody @Valid WorkLogWriteRequest request
    ) {
        workLogEmployeeUpdateService.execute(workLogId, request);

        return MessageResponse.of("업무일지 수정 성공");
    }

    @DeleteMapping("/employee/{workLogId}")
    public MessageResponse deleteWorkLog(@PathVariable Long workLogId) {
        workLogEmployeeDeleteService.execute(workLogId);

        return MessageResponse.of("업무일지 삭제 성공");
    }

    @GetMapping
    public Page<WorkLogListResponse> getWorkLogList(
        @PageableDefault(page = 0, size = 10) Pageable pageable,
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        return workLogAdminQueryListService.execute(pageable, date);
    }

    @GetMapping("/employee")
    public Page<WorkLogListResponse> getEmployeeWorkLogList(
        @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        return workLogEmployeeQueryListService.execute(pageable);
    }

    @GetMapping("/{workLogId}")
    public WorkLogDetailResponse getWorkLogDetail(@PathVariable Long workLogId) {
        return workLogAdminQueryService.execute(workLogId);
    }
}
