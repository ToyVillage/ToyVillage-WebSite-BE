package com.command.toyvillage_server.domain.app.reservation.domain.repository;

import com.command.toyvillage_server.domain.app.reservation.domain.Reservation;
import com.command.toyvillage_server.domain.app.reservation.domain.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findById(Long id);

    List<Reservation> findAll();

    int countByStatus(ReservationStatus status);
}
