package com.command.toyvillage_server.domain.event.presentation;

import com.command.toyvillage_server.domain.event.presentation.dto.request.EventRequest;
import com.command.toyvillage_server.domain.event.presentation.dto.response.EventResponse;
import com.command.toyvillage_server.domain.event.service.*;
import jakarta.validation.Valid;
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
    private final EventCreateService eventCreateService;
    private final EventDeleteService eventDeleteService;
    private final EventUpdateService eventUpdateService;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvent(@RequestBody @Valid EventRequest eventRequest) {
        eventCreateService.execute(eventRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEvent(@PathVariable Long id) {
        eventDeleteService.execute(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEvent(@PathVariable Long id, @RequestBody @Valid EventRequest eventRequest) {
        eventUpdateService.execute(id, eventRequest);
    }
}
