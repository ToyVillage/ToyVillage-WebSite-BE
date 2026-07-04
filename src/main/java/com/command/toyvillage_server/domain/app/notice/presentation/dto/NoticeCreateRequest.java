package com.command.toyvillage_server.domain.app.notice.presentation.dto;

import com.command.toyvillage_server.domain.app.notice.domain.Kind;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NoticeCreateRequest(
    @NotNull(message = "공지사항 제목을 입력해주세요.")
    String title,

    @NotBlank(message = "공지사항 분류를 선택해주세요.")
    Kind kind,

    @NotNull(message = "공지사항 내용을 입력해주세요.")
    String content,

    @NotBlank(message = "공지사항 생성일시를 입력해주세요.")
    LocalDate createAt
) {
}
