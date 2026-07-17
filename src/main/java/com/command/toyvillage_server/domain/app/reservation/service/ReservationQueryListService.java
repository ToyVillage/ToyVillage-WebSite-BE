package com.command.toyvillage_server.domain.app.reservation.service;

import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationRepository;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationQueryListService {
    private final ReservationRepository reservationRepository;

    @Transactional(readOnly = true)
    public List<ReservationListResponse> execute() {
        return reservationRepository.findAll()
            .stream()
            .map(ReservationListResponse::of)
            .toList();
    }
}
