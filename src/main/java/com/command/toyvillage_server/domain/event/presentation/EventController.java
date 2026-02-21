package com.command.toyvillage_server.domain.event.presentation;

import com.command.toyvillage_server.domain.event.presentation.dto.response.EventResponse;
import com.command.toyvillage_server.domain.event.service.EventQueryAllService;
import com.command.toyvillage_server.domain.event.service.EventQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventQueryAllService eventQueryAllService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventResponse> getEvents() {
        return eventQueryAllService.execute();
    }
}
