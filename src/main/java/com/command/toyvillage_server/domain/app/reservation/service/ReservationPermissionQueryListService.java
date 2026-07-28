package com.command.toyvillage_server.domain.app.reservation.service;

import com.command.toyvillage_server.domain.app.reservation.domain.Reservation;
import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationPermissionRepository;
import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationRepository;
import com.command.toyvillage_server.domain.app.reservation.exception.ReservationNotFoundException;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationPermissionResponse;
import com.command.toyvillage_server.domain.common.auth.user.domain.User;
import com.command.toyvillage_server.domain.common.auth.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationPermissionQueryListService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ReservationPermissionRepository reservationPermissionRepository;

    @Transactional(readOnly = true)
    public List<ReservationPermissionResponse> execute(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
            .orElseThrow(() -> ReservationNotFoundException.EXCEPTION);

        List<User> users = reservationPermissionRepository.findAllByReservationId(reservationId);

        List<ReservationPermissionResponse> responses = new ArrayList<>();
        for(User user : users) {
            ReservationPermissionResponse response = ReservationPermissionResponse.of(user.getName());
            responses.add(response);
        }

        return responses;
    }
}
