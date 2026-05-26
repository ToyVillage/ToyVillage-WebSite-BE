package com.command.toyvillage_server.domain.event.service;

import com.command.toyvillage_server.domain.event.domain.Event;
import com.command.toyvillage_server.domain.event.domain.repository.EventRepository;
import com.command.toyvillage_server.domain.event.exception.EventNotFoundException;
import com.command.toyvillage_server.domain.event.presentation.dto.request.EventRequest;
import com.command.toyvillage_server.domain.file.domain.File;
import com.command.toyvillage_server.domain.file.domain.repository.FileRepository;
import com.command.toyvillage_server.domain.file.exception.FileNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventUpdateService {
    private final EventRepository eventRepository;
    private final FileRepository fileRepository;

    @Transactional
    public void execute(Long id, EventRequest eventRequest) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> EventNotFoundException.EXCEPTION);
        File file = fileRepository.findByFileKey(eventRequest.getFileKey()).orElseThrow(() -> FileNotFoundException.EXCEPTION);

        event.update(
                eventRequest.getEventName(),
                eventRequest.getEventDescription(),
                eventRequest.getEventStartDate(),
                eventRequest.getEventEndDate(),
                eventRequest.getEventSubjects(),
                file
        );
        log.info("이벤트 수정 /id : {}",event.getId());
    }
}
