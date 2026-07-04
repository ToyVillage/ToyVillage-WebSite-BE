package com.command.toyvillage_server.domain.app.notice.presentation;

import com.command.toyvillage_server.domain.app.notice.presentation.dto.request.NoticeRequestDto;
import com.command.toyvillage_server.domain.app.notice.presentation.dto.response.NoticeResponseDto;
import com.command.toyvillage_server.domain.app.notice.service.NoticeDeleteService;
import com.command.toyvillage_server.domain.app.notice.service.NoticeUpdateService;
import com.command.toyvillage_server.domain.app.notice.service.QueryNoticeDetailService;
import com.command.toyvillage_server.domain.app.notice.service.QueryNoticeListService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
    private final QueryNoticeDetailService queryNoticeDetailService;
    private final QueryNoticeListService queryNoticeListService;
    private final NoticeUpdateService noticeUpdateService;
    private final NoticeDeleteService noticeDeleteService;

    @PutMapping("/{noticeId}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse update(@Valid @RequestBody NoticeRequestDto request, @PathVariable Long noticeId) {
        noticeUpdateService.execute(noticeId, request);
        return MessageResponse.of("공지 수정 성공");
    }


    @DeleteMapping("/{noticeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<MessageResponse> delete(@PathVariable("noticeId") Long noticeId) {
        noticeDeleteService.execute(noticeId);
        return ResponseEntity.ok(
                new MessageResponse("공지 삭제가 완료되었습니다.")
        );
    }
}

