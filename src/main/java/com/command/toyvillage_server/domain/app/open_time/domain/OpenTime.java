package com.command.toyvillage_server.domain.app.open_time.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Getter
@Entity
@Table(name = "tbl_open_time")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OpenTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_open_time", nullable = false)
    private LocalTime startOpenTime;

    @Column(name = "end_open_time" , nullable = false)
    private LocalTime endOpenTime;

    @Builder
    private OpenTime(LocalTime startOpenTime, LocalTime endOpenTime) {
        this.startOpenTime = startOpenTime;
        this.endOpenTime = endOpenTime;
    }

    public static OpenTime create(LocalTime startOpenTime, LocalTime endOpenTime) {
        return OpenTime.builder()
                .startOpenTime(startOpenTime)
                .endOpenTime(endOpenTime)
                .build();
    }

    public void update(LocalTime startOpenTime, LocalTime endOpenTime) {
        this.startOpenTime = startOpenTime;
        this.endOpenTime = endOpenTime;
    }
}
