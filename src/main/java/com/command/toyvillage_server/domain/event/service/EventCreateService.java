package com.command.toyvillage_server.domain.event.service;

import com.command.toyvillage_server.domain.event.domain.Event;
import com.command.toyvillage_server.domain.event.domain.repository.EventRepository;
import com.command.toyvillage_server.domain.event.presentation.dto.request.EventRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventCreateService {
    private final EventRepository eventRepository;

    @Transactional
    public void execute(EventRequest eventRequest) {
        Event event = Event.builder()
                .title(eventRequest.getEventName())
                .description(eventRequest.getEventDescription())
                .createdAt(eventRequest.getEventStartDate())
                .endedAt(eventRequest.getEventEndDate())
                .subjects(eventRequest.getEventSubjects())
                .build();

        eventRepository.save(event);
    }
}
