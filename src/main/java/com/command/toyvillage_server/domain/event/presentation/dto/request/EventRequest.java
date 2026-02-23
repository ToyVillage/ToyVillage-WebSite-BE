package com.command.toyvillage_server.domain.event.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class EventRequest {
    @NotBlank
    @Size(max = 50)
    @JsonProperty("event_name")
    private String eventName;

    @NotBlank
    @JsonProperty("event_description")
    private String eventDescription;

    @NotNull
    @JsonProperty("event_start_date")
    private LocalDateTime eventStartDate;

    @NotNull
    @JsonProperty("event_end_date")
    private LocalDateTime eventEndDate;

    @NotBlank
    @Size(max = 100)
    @JsonProperty("event_subjects")
    private String eventSubjects;
}
