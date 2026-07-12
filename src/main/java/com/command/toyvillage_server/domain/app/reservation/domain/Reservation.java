package com.command.toyvillage_server.domain.app.reservation.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, name = "leader_count")
    private Integer leaderCount;

    @Column(nullable = false, name = "reservation_count")
    private Integer reservationCount;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false, name = "visit_date")
    private LocalDateTime visitDate;

    @Column(nullable = false, name = "out_time")
    private LocalTime exitTime;

    @Column(nullable = false, name = "visit_site_date")
    private LocalDateTime visitSiteDate;

    @Column(nullable = false, name = "visit_site_exit_time")
    private LocalTime visitSiteExitTime;

    @Column(nullable = false, name = "reservation_date")
    private LocalDate reservationDate;
}
