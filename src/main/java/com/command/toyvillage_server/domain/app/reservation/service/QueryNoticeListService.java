package com.command.toyvillage_server.domain.app.reservation.service;

import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationRepository;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.NoticeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryNoticeListService {
    private final ReservationRepository noticeRepository;

    @Transactional(readOnly = true)
    public List<NoticeResponseDto> execute(Pageable p) {
        Pageable pageable = PageRequest.of(
            p.getPageNumber(),
            p.getPageSize(),
            p.getSortOr(Sort.by(Sort.Direction.DESC, "id"))
        );

        return noticeRepository.findAll(pageable)
            .map(NoticeResponseDto::from)
            .toList();
    }
}
