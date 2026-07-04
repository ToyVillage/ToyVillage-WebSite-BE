package com.command.toyvillage_server.domain.app.notice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoticeNotFoundException extends ResponseStatusException {

    public NoticeNotFoundException(Long noticeId) {
        super(HttpStatus.NOT_FOUND, "공지사항을 찾을 수 없습니다. id=" + noticeId);
    }
}
