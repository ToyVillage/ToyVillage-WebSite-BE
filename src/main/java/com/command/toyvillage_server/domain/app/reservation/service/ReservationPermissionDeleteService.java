package com.command.toyvillage_server.domain.app.reservation.service;

import com.command.toyvillage_server.domain.app.reservation.domain.ReservationPermission;
import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationPermissionRepository;
import com.command.toyvillage_server.domain.app.reservation.exception.ReservationPermissionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationPermissionDeleteService {
    private final ReservationPermissionRepository reservationPermissionRepository;

    @Transactional
    public void execute(Long reservationId, Long userId) {
        ReservationPermission permission = reservationPermissionRepository
            .findByReservation_IdAndUser_Id(reservationId, userId)
            .orElseThrow(() -> ReservationPermissionNotFoundException.EXCEPTION);

        reservationPermissionRepository.delete(permission);
    }
}
