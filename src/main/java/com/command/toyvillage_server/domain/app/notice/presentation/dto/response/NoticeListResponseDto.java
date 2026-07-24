package com.command.toyvillage_server.domain.app.notice.presentation.dto.response;

import com.command.toyvillage_server.domain.app.notice.domain.Kind;
import com.command.toyvillage_server.domain.app.notice.domain.Notice;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record NoticeListResponseDto(
    Long id,
    String title,
    Kind kind,
    LocalDate createdAt
) {
    public static NoticeListResponseDto from(Notice notice) {
        return NoticeListResponseDto.builder()
            .id(notice.getId())
            .title(notice.getTitle())
            .kind(notice.getKind())
            .createdAt(notice.getCreatedAt())
            .build();
    }
}
