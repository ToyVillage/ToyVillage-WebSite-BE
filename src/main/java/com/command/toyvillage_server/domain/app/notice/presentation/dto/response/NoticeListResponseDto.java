package com.command.toyvillage_server.domain.app.notice.presentation.dto.response;

import com.command.toyvillage_server.domain.app.notice.domain.Kind;
import com.command.toyvillage_server.domain.app.notice.domain.Notice;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

@Builder
public record NoticeListResponseDto(
    List<NoticeResponse> notices,
    int totalPageSize
) {
    public static NoticeListResponseDto from(Page<Notice> notices) {
        return NoticeListResponseDto.builder()
            .notices(notices.map(NoticeResponse::from).toList())
            .totalPageSize(notices.getTotalPages())
            .build();
    }

    @Builder
    private record NoticeResponse(
        Long id,
        String title,
        Kind kind,
        LocalDate createdAt
    ) {
        public static NoticeResponse from(Notice notice) {
            return NoticeResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .kind(notice.getKind())
                .createdAt(notice.getCreatedAt())
                .build();
        }
    }
}
