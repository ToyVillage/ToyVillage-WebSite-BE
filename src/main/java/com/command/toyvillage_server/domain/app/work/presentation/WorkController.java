package com.command.toyvillage_server.domain.app.work.presentation;

import com.command.toyvillage_server.domain.app.work.presentation.dto.response.WorkDetailResponse;
import com.command.toyvillage_server.domain.app.work.presentation.dto.response.WorkResponse;
import com.command.toyvillage_server.domain.app.work.service.QueryWorkDetailService;
import com.command.toyvillage_server.domain.app.work.service.QueryWorkListService;
import com.command.toyvillage_server.domain.app.work.service.WorkApproveService;
import com.command.toyvillage_server.domain.app.work.service.WorkRejectService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/work")
public class WorkController {

    private final QueryWorkListService queryWorkListService;
    private final QueryWorkDetailService queryWorkDetailService;
    private final WorkApproveService workApproveService;
    private final WorkRejectService workRejectService;

    @GetMapping
    public List<WorkResponse> getWorks() {
        return queryWorkListService.execute();
    }

    @GetMapping("/{id}")
    public WorkDetailResponse getWorkById(@PathVariable Long id) {
        return queryWorkDetailService.execute(id);
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
}
