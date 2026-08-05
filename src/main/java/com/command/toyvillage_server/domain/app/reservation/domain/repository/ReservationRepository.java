package com.command.toyvillage_server.domain.app.reservation.domain.repository;

import com.command.toyvillage_server.domain.app.reservation.domain.Reservation;
import com.command.toyvillage_server.domain.app.reservation.domain.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findById(Long id);

    int countByStatus(ReservationStatus status);

    @Modifying
    @Query("""
        update Reservation r
        set r.status = :status
        where r.visitSiteDate >= :today
          and r.visitDate >= :today
          and (r.status is null or r.status <> :status)
        """)
    int updateBeforeSiteVisitStatus(
        @Param("today") LocalDate today,
        @Param("status") ReservationStatus status
    );

    @Modifying
    @Query("""
        update Reservation r
        set r.status = :status
        where r.visitSiteDate < :today
          and r.visitDate >= :today
          and (r.status is null or r.status <> :status)
        """)
    int updateSiteVisitCompletedStatus(
        @Param("today") LocalDate today,
        @Param("status") ReservationStatus status
    );

    @Modifying
    @Query("""
        update Reservation r
        set r.status = :status
        where r.visitDate < :today
          and (r.status is null or r.status <> :status)
        """)
    int updateVisitCompletedStatus(
        @Param("today") LocalDate today,
        @Param("status") ReservationStatus status
    );
}
