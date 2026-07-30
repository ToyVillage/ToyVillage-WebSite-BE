package com.command.toyvillage_server.domain.app.reservation.service;

import com.command.toyvillage_server.domain.app.auth.account.domain.AppAccount;
import com.command.toyvillage_server.domain.app.auth.account.domain.repository.AppAccountRepository;
import com.command.toyvillage_server.domain.app.auth.account.exception.AppAccountNotFoundException;
import com.command.toyvillage_server.domain.app.reservation.domain.Reservation;
import com.command.toyvillage_server.domain.app.reservation.domain.ReservationPermission;
import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationPermissionRepository;
import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationRepository;
import com.command.toyvillage_server.domain.app.reservation.exception.ReservationNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationPermissionSettingService {
    private final ReservationRepository reservationRepository;
    private final ReservationPermissionRepository reservationPermissionRepository;
    private final AppAccountRepository appAccountRepository;

    @Transactional
    public void execute(Long reservationId, Long appAccountId, boolean permission) {
        Reservation reservation = reservationRepository.findById(reservationId)
            .orElseThrow(() -> ReservationNotFoundException.EXCEPTION);

        AppAccount appAccount = appAccountRepository.findById(appAccountId)
            .orElseThrow(() -> AppAccountNotFoundException.EXCEPTION);

        if (permission) {
            grant(reservation, appAccount);
        } else {
            revoke(reservationId, appAccountId);
        }
    }

    private void grant(Reservation reservation, AppAccount appAccount) {
        if (!reservationPermissionRepository.existsByReservation_IdAndAppAccount_Id(
                reservation.getId(),
                appAccount.getId()
        )) {
            reservationPermissionRepository.save(
                ReservationPermission.builder()
                    .reservation(reservation)
                    .appAccount(appAccount)
                    .build()
            );
        }
    }

    private void revoke(Long reservationId, Long appAccountId) {
        reservationPermissionRepository
            .findByReservation_IdAndAppAccount_Id(reservationId, appAccountId)
            .ifPresent(reservationPermissionRepository::delete);
    }
}
