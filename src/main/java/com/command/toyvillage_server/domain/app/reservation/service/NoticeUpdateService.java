package com.command.toyvillage_server.domain.app.reservation.service;

import com.command.toyvillage_server.domain.app.reservation.domain.Reservation;
import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationRepository;
import com.command.toyvillage_server.domain.app.reservation.exception.NoticeNotFoundException;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.request.NoticeRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoticeUpdateService {
    private final ReservationRepository noticeRepository;

    @Transactional
    public void execute(Long id, NoticeRequestDto dto) {
        Reservation notice = noticeRepository.findById(id)
            .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        notice.update(dto.getTitle(), dto.getKind(), dto.getContent());
        noticeRepository.save(notice);
    }
}
