package com.command.toyvillage_server.domain.app.notice.presentation;

import com.command.toyvillage_server.domain.app.notice.presentation.dto.NoticeCreateRequest;
import com.command.toyvillage_server.domain.app.notice.service.NoticeCreateService;
import com.command.toyvillage_server.domain.app.notice.service.NoticeDeleteService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeDeleteService noticeDeleteService;
    private final NoticeCreateService noticeCreateService;

    @DeleteMapping("/{noticeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<MessageResponse> deleteNotice(@PathVariable("noticeId") Long noticeId) {
        noticeDeleteService.execute(noticeId);
        return ResponseEntity.ok(
                new MessageResponse("공지 삭제가 완료되었습니다.")
        );
    }

    @PostMapping()
    public ResponseEntity<MessageResponse> createNotice(@RequestBody NoticeCreateRequest request) {
        noticeCreateService.execute(request);

        return ResponseEntity.ok(
            new MessageResponse("공지사항이 생성되었습니다.")
        );
    }
}