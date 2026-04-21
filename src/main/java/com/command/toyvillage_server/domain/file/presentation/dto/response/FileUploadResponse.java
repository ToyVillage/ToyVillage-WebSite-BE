package com.command.toyvillage_server.domain.file.presentation.dto.response;

import lombok.Builder;

@Builder
public record FileUploadResponse (
    String fileKey
) { }
