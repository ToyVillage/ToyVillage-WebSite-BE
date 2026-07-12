package com.command.toyvillage_server.domain.app.reservation.service;

import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationQueryService {
    private final ReservationRepository reservationRepository;

    @Transactional(readOnly = true)
    public
}
