package com.command.toyvillage_server.domain.app.notice.presentation.dto.request;

import com.command.toyvillage_server.domain.app.notice.domain.Kind;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class NoticeRequestDto {
    @NotBlank(message = "공지사항 제목을 입력해주세요.")
    private String title;

    @NotNull(message = "공지사항 분류를 선택해주세요.")
    private Kind kind;

    @NotBlank(message = "공지사항 내용을 입력해주세요.")
    private String content;
}