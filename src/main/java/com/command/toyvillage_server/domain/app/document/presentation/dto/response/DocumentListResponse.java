package com.command.toyvillage_server.domain.app.document.presentation.dto.response;

import com.command.toyvillage_server.domain.app.document.domain.Document;
import com.command.toyvillage_server.domain.app.document.domain.DocumentType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DocumentListResponse(
    Long id,
    String title,
    DocumentType type,
    LocalDateTime createdAt
) {
    public static DocumentListResponse from(Document document) {
        return DocumentListResponse.builder()
            .id(document.getId())
            .title(document.getTitle())
            .type(document.getType())
            .createdAt(document.getCreatedAt())
            .build();
    }
}
