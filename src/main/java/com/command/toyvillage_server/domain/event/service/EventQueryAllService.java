package com.command.toyvillage_server.domain.event.service;

import com.command.toyvillage_server.domain.event.domain.Event;
import com.command.toyvillage_server.domain.event.domain.repository.EventRepository;
import com.command.toyvillage_server.domain.event.presentation.dto.response.EventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class EventQueryAllService {
    private final EventRepository eventRepository;

    @Transactional(readOnly = true)
    public List<EventResponse> execute() {
        return eventRepository.findAll()
                .stream()
                .map(EventResponse::new)
                .collect(Collectors.toList());
    }
}
