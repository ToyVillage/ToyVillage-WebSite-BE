package com.command.toyvillage_server.domain.app.notice.presentation.dto.response;

import com.command.toyvillage_server.domain.app.notice.domain.Kind;
import com.command.toyvillage_server.domain.app.notice.domain.Notice;
import com.command.toyvillage_server.domain.web.file.presentation.dto.response.FileResponse;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record NoticeDetailResponse(
    Long id,
    String title,
    Kind kind,
    String content,
    LocalDate createdAt,
    List<FileResponse> files
) {
    public static NoticeDetailResponse from(Notice notice) {
        return NoticeDetailResponse.builder()
            .id(notice.getId())
            .title(notice.getTitle())
            .kind(notice.getKind())
            .content(notice.getContent())
            .createdAt(notice.getCreatedAt())
            .files(
                notice.getFiles().stream()
                    .map(FileResponse::from)
                    .toList()
            )
            .build();
    }
}
