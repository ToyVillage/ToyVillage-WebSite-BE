package com.command.toyvillage_server.domain.event.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {
    private Long event_id;
    private String event_name;
    private String event_description;
    private String event_start_date;
    private LocalDateTime event_end_date;
    private LocalDateTime event_subjects;
}
