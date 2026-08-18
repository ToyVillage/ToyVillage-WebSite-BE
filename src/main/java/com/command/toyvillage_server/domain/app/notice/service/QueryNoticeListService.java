package com.command.toyvillage_server.domain.app.notice.service;

import com.command.toyvillage_server.domain.app.notice.domain.Notice;
import com.command.toyvillage_server.domain.app.notice.domain.repository.NoticeRepository;
import com.command.toyvillage_server.domain.app.notice.presentation.dto.response.NoticeListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryNoticeListService {
    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
    public NoticeListResponseDto execute(Pageable p) {
        Pageable pageable = PageRequest.of(
            p.getPageNumber(),
            p.getPageSize(),
            p.getSortOr(Sort.by(Sort.Direction.DESC, "id"))
        );

        Page<Notice> notices = noticeRepository.findAll(pageable);

        return NoticeListResponseDto.from(notices);
    }
}
