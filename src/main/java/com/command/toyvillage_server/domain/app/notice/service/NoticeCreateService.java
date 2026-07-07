package com.command.toyvillage_server.domain.app.notice.service;

import com.command.toyvillage_server.domain.app.notice.domain.Notice;
import com.command.toyvillage_server.domain.app.notice.domain.repository.NoticeRepository;
import com.command.toyvillage_server.domain.app.notice.presentation.dto.request.NoticeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeCreateService {
    private final NoticeRepository noticeRepository;

    @Transactional
    public void execute(NoticeRequestDto request) {
        Notice notice = Notice.create(
            request.getTitle(),
            request.getKind(),
            request.getContent()
        );

        noticeRepository.save(notice);
    }
}
