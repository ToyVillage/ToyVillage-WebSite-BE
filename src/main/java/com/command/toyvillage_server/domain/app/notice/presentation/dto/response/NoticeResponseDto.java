package com.command.toyvillage_server.domain.app.notice.presentation.dto.response;

import com.command.toyvillage_server.domain.app.notice.domain.Kind;
import com.command.toyvillage_server.domain.app.notice.domain.Notice;
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
    public static NoticeResponseDto from(Notice notice) {
        return NoticeResponseDto.builder()
            .id(notice.getId())
            .title(notice.getTitle())
            .kind(notice.getKind())
            .content(notice.getContent())
            .createdAt(notice.getCreatedAt())
            .build();
    }
}
