package com.command.toyvillage_server.domain.app.reservation.service;

import com.command.toyvillage_server.domain.app.reservation.domain.Reservation;
import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationRepository;
import com.command.toyvillage_server.domain.app.reservation.exception.NoticeNotFoundException;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.NoticeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryNoticeDetailService {
    private final ReservationRepository noticeRepository;

    @Transactional(readOnly = true)
    public NoticeResponseDto execute(Long id) {
        Reservation notice = noticeRepository.findById(id)
            .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        return NoticeResponseDto.from(notice);
    }
}
