    package com.command.toyvillage_server.domain.event.service;

    import com.command.toyvillage_server.domain.event.domain.repository.EventRepository;
    import com.command.toyvillage_server.domain.event.presentation.dto.response.EventResponse;
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import java.util.List;
    import java.util.stream.Collectors;

    @Slf4j
    @Service
    @RequiredArgsConstructor

    public class EventQueryAllService {
        private final EventRepository eventRepository;

        @Transactional(readOnly = true)
        public List<EventResponse> execute() {
            return eventRepository.findAll()
                    .stream()
                    .map(EventResponse::from)
                    .collect(Collectors.toList());
        }
    }
