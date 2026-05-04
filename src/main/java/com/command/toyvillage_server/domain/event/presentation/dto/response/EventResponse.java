    package com.command.toyvillage_server.domain.event.presentation.dto.response;

    import com.command.toyvillage_server.domain.event.domain.Event;
    import com.command.toyvillage_server.domain.file.domain.File;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import java.time.LocalDateTime;

    @Getter
    @AllArgsConstructor
    public class EventResponse {
        private Long eventId;
        private String eventName;
        private String eventDescription;
        private LocalDateTime eventStartDate;
        private LocalDateTime eventEndDate;
        private String eventSubjects;
        private File fileKey;

        public static EventResponse from(Event event) {
            return new EventResponse(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getStartDate(),
                event.getEndDate(),
                event.getSubjects(),
                    event.getFile()
            );
        }
    }
