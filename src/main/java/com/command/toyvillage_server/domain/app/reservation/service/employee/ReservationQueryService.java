package com.command.toyvillage_server.domain.app.reservation.service.employee;

import com.command.toyvillage_server.domain.app.reservation.domain.Reservation;
import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationRepository;
import com.command.toyvillage_server.domain.app.reservation.exception.ReservationNotFoundException;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationQueryService {
    private final ReservationRepository reservationRepository;

    @Transactional(readOnly = true)
    public ReservationResponse execute(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
            .orElseThrow(() -> ReservationNotFoundException.EXCEPTION);

        return ReservationResponse.from(reservation);
    }
}
