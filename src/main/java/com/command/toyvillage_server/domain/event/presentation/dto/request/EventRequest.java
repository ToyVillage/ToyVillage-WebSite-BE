package com.command.toyvillage_server.domain.event.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EventRequest {
    @NotBlank
    @Size(max = 50)
    private String event_name;

    @NotBlank
    private String event_description;

    @NotNull
    private LocalDateTime event_start_date;

    @NotNull
    private LocalDateTime event_end_date;

    @NotBlank
    @Size(max = 100)
    private String event_subjects;

}
