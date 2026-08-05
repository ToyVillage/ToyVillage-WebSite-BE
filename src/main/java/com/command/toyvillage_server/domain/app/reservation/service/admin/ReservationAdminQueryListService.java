package com.command.toyvillage_server.domain.app.reservation.service.admin;

import com.command.toyvillage_server.domain.app.reservation.domain.Reservation;
import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationRepository;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationAdminQueryListObjectResponse;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationAdminQueryListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationAdminQueryListService {
    private final ReservationRepository reservationRepository;

    @Transactional
    public ReservationAdminQueryListResponse execute() {
        int beforeVisitSite = reservationRepository.countByVisitSiteDateBefore(LocalDate.now());
        int doneVisitSite = reservationRepository.countByVisitSiteDateAfter(LocalDate.now());
        int doneVisit = reservationRepository.countByVisitDateAfter(LocalDate.now());

        List<Reservation> reservations = reservationRepository.findAll();

        ReservationAdminQueryListObjectResponse reservationList = ReservationAdminQueryListObjectResponse.of()
    }
}
