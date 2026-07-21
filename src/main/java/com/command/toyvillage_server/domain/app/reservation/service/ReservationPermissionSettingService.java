package com.command.toyvillage_server.domain.app.reservation.service;

import com.command.toyvillage_server.domain.app.reservation.domain.Reservation;
import com.command.toyvillage_server.domain.app.reservation.domain.ReservationPermission;
import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationPermissionRepository;
import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationRepository;
import com.command.toyvillage_server.domain.app.reservation.exception.ReservationNotFoundException;
import com.command.toyvillage_server.domain.common.auth.user.domain.User;
import com.command.toyvillage_server.domain.common.auth.user.domain.repository.UserRepository;
import com.command.toyvillage_server.domain.common.auth.user.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationPermissionSettingService {
    private final ReservationRepository reservationRepository;
    private final ReservationPermissionRepository reservationPermissionRepository;
    private final UserRepository userRepository;

    @Transactional
    public void execute(Long reservationId, Long userId, boolean permission) {
        Reservation reservation = reservationRepository.findById(reservationId)
            .orElseThrow(() -> ReservationNotFoundException.EXCEPTION);

        User user = userRepository.findById(userId)
            .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (permission) {
            grant(reservation, user);
        } else {
            reservationPermissionRepository.deleteByReservationIdAndUserId(reservationId, userId);
        }
    }

    private void grant(Reservation reservation, User user) {
        if (!reservationPermissionRepository.existsByReservationIdAndUserId(reservation.getId(), user.getId())) {
            reservationPermissionRepository.save(
                ReservationPermission.builder()
                    .reservation(reservation)
                    .user(user)
                    .build()
            );
        }
    }
}
