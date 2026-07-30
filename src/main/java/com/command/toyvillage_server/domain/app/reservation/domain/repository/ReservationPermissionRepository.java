package com.command.toyvillage_server.domain.app.reservation.domain.repository;

import com.command.toyvillage_server.domain.app.reservation.domain.Reservation;
import com.command.toyvillage_server.domain.app.reservation.domain.ReservationPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ReservationPermissionRepository extends JpaRepository<ReservationPermission, Long> {
    boolean existsByReservation_IdAndAppAccount_Id(Long reservationId, Long appAccountId);

    @Query("select rp.reservation from ReservationPermission rp where rp.appAccount.id = :appAccountId")
    List<Reservation> findReservationsByAppAccountId(@Param("appAccountId") Long appAccountId);

    List<ReservationPermission> findAllByReservation_Id(Long reservationId);

    Optional<ReservationPermission> findByReservation_IdAndAppAccount_Id(
            Long reservationId,
            Long appAccountId
    );
}
