package com.command.toyvillage_server.domain.app.reservation.service.admin;

import com.command.toyvillage_server.domain.app.reservation.domain.Reservation;
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

    @Transactional(readOnly = true)
    public ReservationAdminQueryListResponse execute() {
        LocalDate today = LocalDate.now();

        int beforeVisitSite = reservationRepository.countByVisitSiteDateGreaterThanEqual(today);
        int doneVisitSite = reservationRepository
            .countByVisitSiteDateBeforeAndVisitDateGreaterThanEqual(today, today);
        int doneVisit = reservationRepository.countByVisitDateBefore(today);

        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationAdminQueryListObjectResponse> reservationAdminQueryListObjectResponseList = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationAdminQueryListObjectResponseList.add(ReservationAdminQueryListObjectResponse.from(reservation));
        }

        return ReservationAdminQueryListResponse.of(beforeVisitSite, doneVisitSite, doneVisit, reservationAdminQueryListObjectResponseList);
    }
}
