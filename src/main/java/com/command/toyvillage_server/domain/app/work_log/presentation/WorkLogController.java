package com.command.toyvillage_server.domain.app.work_log.presentation;

import com.command.toyvillage_server.domain.app.work_log.presentation.dto.request.WorkLogTemplateCreateRequest;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogDetailResponse;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogListResponse;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogTemplateDetailResponse;
import com.command.toyvillage_server.domain.app.work_log.presentation.dto.response.WorkLogTemplateListResponse;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/work-log")
@RequiredArgsConstructor
public class WorkLogController {
    private final WorkLogCreateService workLogCreateService;
    private final WorkLogEmployeeQueryListService workLogEmployeeQueryListService;
    private final WorkLogAdminQueryListService workLogAdminQueryListService;
    private final WorkLogQueryService workLogQueryService;
    private final WorkLogTemplateCreateService workLogTemplateCreateService;
    private final WorkLogTemplateQueryListService workLogTemplateQueryListService;
    private final WorkLogTemplateQueryService workLogTemplateQueryService;

    @PostMapping("/employee")
    public ResponseEntity<MessageResponse> createWorkLog(
            @RequestBody @Valid WorkLogCreateRequest request
    ) {
        workLogCreateService.execute(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.of("업무일지 생성 성공"));
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

    @PostMapping("/template")
    public ResponseEntity<MessageResponse> createWorkLogTemplate(
            @RequestBody @Valid WorkLogTemplateCreateRequest request
    ) {
        workLogTemplateCreateService.execute(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.of("업무일지 양식 생성 성공"));
    }

    @GetMapping("/template")
    public Page<WorkLogTemplateListResponse> getWorkLogTemplateList(
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        return workLogTemplateQueryListService.execute(pageable);
    }

    @GetMapping("/template/{workLogTemplateId}")
    public WorkLogTemplateDetailResponse getWorkLogTemplateDetail(
            @PathVariable Long workLogTemplateId
    ) {
        return workLogTemplateQueryService.execute(workLogTemplateId);
    }
}
