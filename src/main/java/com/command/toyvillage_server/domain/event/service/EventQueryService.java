package com.command.toyvillage_server.domain.event.service;

import com.command.toyvillage_server.domain.event.domain.Event;
import com.command.toyvillage_server.domain.event.domain.repository.EventRepository;
import com.command.toyvillage_server.domain.event.presentation.dto.response.EventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventQueryService {
    private final EventRepository eventRepository;

    @Transactional(readOnly = true)
    public EventResponse execute(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 이벤트를 찾을수 없습니다."));
        return new EventResponse(event);
    }
}
