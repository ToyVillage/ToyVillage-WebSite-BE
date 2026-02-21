package com.command.toyvillage_server.domain.event.service;

import com.command.toyvillage_server.domain.event.domain.Event;
import com.command.toyvillage_server.domain.event.domain.repository.EventRepository;
import com.command.toyvillage_server.domain.event.presentation.dto.response.EventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class EventQueryAllService {
    private final EventRepository eventRepository;

    @Transactional(readOnly = true)
    public List<EventResponse> execute() {
        List<Event> events = eventRepository.findAll();

        List<EventResponse> EventAllResponses = events.stream()
                .map(event ->
                        EventResponse.builder()
                                .event_id(event.getId())
                                .event_name(event.getTitle())
                                .event_description(event.getDescription())
                                .event_start_date(event.getCreatedAt())
                                .event_end_date(event.getEndedAt())
                                .event_subjects(event.getSubjects())
                                .build()
                ).toList();
    return EventAllResponses;
    }
}
