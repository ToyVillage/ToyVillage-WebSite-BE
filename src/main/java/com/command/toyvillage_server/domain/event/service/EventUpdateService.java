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
public class EventUpdateService {
    private final EventRepository eventRepository;

    @Transactional
    public void execute(Long id, EventRequest eventRequest) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 수정할 이벤트를 찾을 수 없습니다."));

        event.update(
                eventRequest.getEventName(),
                eventRequest.getEventDescription(),
                eventRequest.getEventStartDate(),
                eventRequest.getEventEndDate(),
                eventRequest.getEventSubjects()
        );
    }
}
