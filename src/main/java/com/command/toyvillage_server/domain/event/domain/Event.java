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
    private LocalDateTime startDate;

    @Column(name = "event_end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "event_subjects", nullable = false)
    private String subjects;

    public void update(EventRequest eventRequest) {
        this.title = eventRequest.getEventName();
        this.description = eventRequest.getEventDescription();
        this.startDate = eventRequest.getEventStartDate();
        this.endDate = eventRequest.getEventEndDate();
        this.subjects = eventRequest.getEventSubjects();
    }
}
