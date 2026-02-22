package com.command.toyvillage_server.domain.event.service;

import com.command.toyvillage_server.domain.event.domain.Event;
import com.command.toyvillage_server.domain.event.domain.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventDeleteService {
    private final EventRepository eventRepository;

    @Transactional
    public void execute(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("삭제할 이벤트를 찾을수 없습니다."));
        eventRepository.delete(event);
    }
}
