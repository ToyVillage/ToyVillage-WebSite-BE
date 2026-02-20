package com.command.toyvillage_server.domain.event.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_event")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Event {
    @Id
    @Column(name = "event_id", nullable = false)
    private Long id;

    @Column(name = "event_name",nullable = false)
    private String title;

    @Column(name = "event_description", nullable = false)
    private String description;

    @Column(name = "event_start_date", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "event_end_date", nullable = false)
    private LocalDateTime endedAt;

    @Column(name = "event_subjects", nullable = false)
    private String subjects;

}
