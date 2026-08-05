package com.command.toyvillage_server.domain.app.reservation.service.admin;

import com.command.toyvillage_server.domain.app.reservation.domain.Reservation;
import com.command.toyvillage_server.domain.app.reservation.domain.ReservationStatus;
import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationRepository;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationAdminQueryListObjectResponse;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationAdminQueryListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationAdminQueryListService {
    private final ReservationRepository reservationRepository;

    @Transactional
    public ReservationAdminQueryListResponse execute() {
        LocalDate today = LocalDate.now();
        List<Reservation> reservations = reservationRepository.findAll();

        reservations.forEach(reservation -> reservation.updateStatus(today));

        int beforeVisitSite = reservationRepository.countByStatus(ReservationStatus.BEFORE_SITE_VISIT);
        int doneVisitSite = reservationRepository.countByStatus(ReservationStatus.SITE_VISIT_COMPLETED);
        int doneVisit = reservationRepository.countByStatus(ReservationStatus.VISIT_COMPLETED);

        List<ReservationAdminQueryListObjectResponse> reservationAdminQueryListObjectResponseList = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationAdminQueryListObjectResponseList.add(ReservationAdminQueryListObjectResponse.from(reservation));
        }

        return ReservationAdminQueryListResponse.of(beforeVisitSite, doneVisitSite, doneVisit, reservationAdminQueryListObjectResponseList);
    }
}
