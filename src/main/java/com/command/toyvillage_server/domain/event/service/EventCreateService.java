package com.command.toyvillage_server.domain.event.service;

import com.command.toyvillage_server.domain.event.domain.Event;
import com.command.toyvillage_server.domain.event.domain.repository.EventRepository;
import com.command.toyvillage_server.domain.event.presentation.dto.request.EventRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventCreateService {
    private final EventRepository eventRepository;

    @Transactional
    public void execute(EventRequest eventRequest) {
        Event event = Event.builder()
                .title(eventRequest.getEvent_name())
                .description(eventRequest.getEvent_description())
                .createdAt(eventRequest.getEvent_start_date())
                .endedAt(eventRequest.getEvent_end_date())
                .subjects(eventRequest.getEvent_subjects())
                .build();

        eventRepository.save(event);
    }
}
