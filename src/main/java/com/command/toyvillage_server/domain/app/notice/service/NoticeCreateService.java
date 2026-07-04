package com.command.toyvillage_server.domain.app.notice.service;

import com.command.toyvillage_server.domain.app.notice.domain.Notice;
import com.command.toyvillage_server.domain.app.notice.domain.repository.NoticeRepository;
import com.command.toyvillage_server.domain.app.notice.presentation.dto.NoticeCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeCreateService {
    private final NoticeRepository noticeRepository;

    @Transactional
    public void execute(NoticeCreateRequest request) {
        Notice notice = Notice.createNotice(
            request.title(),
            request.kind(),
            request.content(),
            request.createAt()
        );

        noticeRepository.save(notice);
    }
}
