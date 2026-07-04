package com.command.toyvillage_server.domain.app.notice.presentation.dto.request;

import com.command.toyvillage_server.domain.app.notice.domain.Kind;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class NoticeRequestDto {
    @NotBlank
    private String title;

    @NotNull
    private Kind kind;

    @NotBlank
    private String content;
}