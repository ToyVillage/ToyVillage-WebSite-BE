package com.command.toyvillage_server.domain.app.reservation.presentation.dto.response;

import com.command.toyvillage_server.domain.app.reservation.domain.Reservation;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record NoticeResponseDto(
    Long id,
    String title,
    Kind kind,
    String content,
    LocalDate createdAt
) {
    public static NoticeResponseDto from(Reservation notice) {
        return NoticeResponseDto.builder()
            .id(notice.getId())
            .title(notice.getTitle())
            .kind(notice.getKind())
            .content(notice.getContent())
            .createdAt(notice.getCreatedAt())
            .build();
    }
}
