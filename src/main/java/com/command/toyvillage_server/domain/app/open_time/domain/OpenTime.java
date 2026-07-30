package com.command.toyvillage_server.domain.app.open_time.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Entity
@Table(name = "tbl_open_time")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OpenTime {
    private static final LocalTime DEFAULT_START_OPEN_TIME = LocalTime.of(11, 0);
    private static final LocalTime DEFAULT_END_OPEN_TIME = LocalTime.of(18, 0);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "open_date", nullable = false)
    private LocalDate openDate;

    @Column(name = "start_open_time", nullable = false)
    private LocalTime startOpenTime;

    @Column(name = "end_open_time" , nullable = false)
    private LocalTime endOpenTime;

    @Builder
    private OpenTime(LocalDate openDate, LocalTime startOpenTime, LocalTime endOpenTime) {
        this.openDate = openDate;
        this.startOpenTime = startOpenTime;
        this.endOpenTime = endOpenTime;
    }

    public static OpenTime create(LocalDate openDate) {
        return OpenTime.builder()
                .openDate(openDate)
                .startOpenTime(DEFAULT_START_OPEN_TIME)
                .endOpenTime(DEFAULT_END_OPEN_TIME)
                .build();
    }

    public void update(LocalDate openDate, LocalTime startOpenTime, LocalTime endOpenTime) {
        this.openDate = openDate;
        this.startOpenTime = startOpenTime;
        this.endOpenTime = endOpenTime;
    }
}
