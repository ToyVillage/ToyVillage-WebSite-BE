package com.command.toyvillage_server.domain.app.reservation.domain.repository;

import com.command.toyvillage_server.domain.app.reservation.domain.ReservationPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationPermissionRepository extends JpaRepository<ReservationPermission, Long> {
    boolean existsByReservationIdAndUserId(Long reservationId, Long userId);
    void deleteByReservationIdAndUserId(Long reservationId, Long userId);
}
