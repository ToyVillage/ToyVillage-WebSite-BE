package com.command.toyvillage_server.domain.event.presentation;

import com.command.toyvillage_server.domain.event.presentation.dto.response.EventResponse;
import com.command.toyvillage_server.domain.event.service.EventQueryAllService;
import com.command.toyvillage_server.domain.event.service.EventQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventQueryAllService eventQueryAllService;
    private final EventQueryService eventQueryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventResponse> getQueryAllEvents() {
        return eventQueryAllService.execute();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventResponse getQueryEvents(@PathVariable Long id) {
        return eventQueryService.execute(id);
    }
}
