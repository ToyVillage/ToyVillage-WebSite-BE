package com.command.toyvillage_server.domain.event.domain;

import com.command.toyvillage_server.domain.event.presentation.dto.request.EventRequest;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void update(EventRequest eventRequest) {
        this.title = eventRequest.getEvent_name();
        this.description = eventRequest.getEvent_description();
        this.createdAt = eventRequest.getEvent_start_date();
        this.endedAt = eventRequest.getEvent_end_date();
        this.subjects = eventRequest.getEvent_subjects();
    }


}
