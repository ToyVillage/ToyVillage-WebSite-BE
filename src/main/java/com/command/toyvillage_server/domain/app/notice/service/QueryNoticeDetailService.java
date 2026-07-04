package com.command.toyvillage_server.domain.app.notice.service;

import com.command.toyvillage_server.domain.app.notice.domain.Notice;
import com.command.toyvillage_server.domain.app.notice.domain.repository.NoticeRepository;
import com.command.toyvillage_server.domain.app.notice.exception.NoticeNotFoundException;
import com.command.toyvillage_server.domain.app.notice.presentation.dto.response.NoticeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryNoticeDetailService {
    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
    public NoticeResponseDto execute(Long id) {
        Notice notice = noticeRepository.findById(id)
            .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        return NoticeResponseDto.from(notice);
    }
}
