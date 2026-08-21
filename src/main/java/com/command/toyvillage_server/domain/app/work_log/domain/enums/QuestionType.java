package com.command.toyvillage_server.domain.app.work_log.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum QuestionType {
    SHORT_TEXT("주관식"),
    LONG_TEXT("장문형"),
    MULTIPLE_CHOICE("객관식"),
    CHECK_BOX("체크박스"),
    DROP_DOWN("드롭다운"),
    FILE_UPLOAD("파일 업로드");

    private final String description;
}
