package com.command.toyvillage_server.domain.event.service;

import com.command.toyvillage_server.domain.event.domain.Event;
import com.command.toyvillage_server.domain.event.domain.repository.EventRepository;
import com.command.toyvillage_server.domain.event.presentation.dto.request.EventRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventUpdateService {
    private final EventRepository eventRepository;

    @Transactional
    public void execute(Long id, EventRequest eventRequest) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 수정할 이벤트을 찾을수 없습니다."));
        event.update(eventRequest);
    }

}
