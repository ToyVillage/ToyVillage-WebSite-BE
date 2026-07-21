package com.command.toyvillage_server.domain.app.reservation.service;

import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationPermissionRepository;
import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationRepository;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationListResponse;
import com.command.toyvillage_server.global.security.auth.CustomAppUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationQueryListService {
    private final ReservationRepository reservationRepository;
    private final ReservationPermissionRepository reservationPermissionRepository;

    @Transactional(readOnly = true)
    public List<ReservationListResponse> execute() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof CustomAppUserDetails userDetails) {
            return reservationPermissionRepository.findReservationsByUserId(userDetails.getId())
                .stream()
                .map(ReservationListResponse::from)
                .toList();
        }

        return reservationRepository.findAll()
            .stream()
            .map(ReservationListResponse::from)
            .toList();
    }
}
