package com.command.toyvillage_server.domain.app.reservation.service;

import com.command.toyvillage_server.domain.app.reservation.domain.Reservation;
import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationRepository;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.request.NoticeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeCreateService {
    private final ReservationRepository noticeRepository;

    @Transactional
    public void execute(NoticeRequestDto request) {
        Reservation notice = Reservation.create(
            request.getTitle(),
            request.getKind(),
            request.getContent()
        );

        noticeRepository.save(notice);
    }
}
