    package com.command.toyvillage_server.domain.event.presentation.dto.response;

    import com.command.toyvillage_server.domain.event.domain.Event;
    import lombok.Getter;

    import java.time.LocalDateTime;

    @Getter
    public class EventResponse {
        private Long event_id;
        private String event_name;
        private String event_description;
        private LocalDateTime event_start_date;
        private LocalDateTime event_end_date;
        private String event_subjects;


        public EventResponse(Event event) {
            this.event_id = event.getId();
            this.event_name = event.getTitle();
            this.event_description = event.getDescription();
            this.event_start_date = event.getCreatedAt();
            this.event_end_date = event.getEndedAt();
            this.event_subjects = event.getSubjects();
        }
    }
