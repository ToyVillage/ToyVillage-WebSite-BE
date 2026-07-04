package com.command.toyvillage_server.domain.app.notice.service;

import com.command.toyvillage_server.domain.app.notice.domain.Notice;
import com.command.toyvillage_server.domain.app.notice.domain.repository.NoticeRepository;
import com.command.toyvillage_server.domain.app.notice.exception.NoticeNotFoundException;
import com.command.toyvillage_server.domain.app.notice.presentation.dto.request.NoticeRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoticeUpdateService {
    private final NoticeRepository noticeRepository;

    @Transactional
    public void execute(Long id, NoticeRequestDto dto) {
        Notice notice = noticeRepository.findById(id)
            .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        notice.update(dto.getTitle(), dto.getKind(), dto.getContent());
        noticeRepository.save(notice);
    }
}
