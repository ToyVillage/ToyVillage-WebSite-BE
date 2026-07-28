package com.command.toyvillage_server.domain.app.reservation.domain.repository;

import com.command.toyvillage_server.domain.app.reservation.domain.Reservation;
import com.command.toyvillage_server.domain.app.reservation.domain.ReservationPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationPermissionRepository extends JpaRepository<ReservationPermission, Long> {
    boolean existsByReservation_IdAndUser_Id(Long reservationId, Long userId);

    @Query("select rp.reservation from ReservationPermission rp where rp.user.id = :userId")
    List<Reservation> findReservationsByUserId(@Param("userId") Long userId);

    List<ReservationPermission> findAllByReservation_Id(Long reservationId);

    Optional<ReservationPermission> findByReservation_IdAndUser_Id(Long reservationId, Long userId);
}
