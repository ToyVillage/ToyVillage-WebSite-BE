package com.command.toyvillage_server.domain.app.notice.presentation;

import com.command.toyvillage_server.domain.app.notice.service.NoticeDeleteService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeDeleteService noticeDeleteService;

    @DeleteMapping("/{noticeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<MessageResponse> delete(@PathVariable("noticeId") Long noticeId) {
        noticeDeleteService.execute(noticeId);
        return ResponseEntity.ok(
                new MessageResponse("공지 삭제가 완료되었습니다.")
        );
    }
}

