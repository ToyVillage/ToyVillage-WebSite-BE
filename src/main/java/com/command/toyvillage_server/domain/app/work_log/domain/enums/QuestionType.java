package com.command.toyvillage_server.domain.app.work_log.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum QuestionType {
    SHORT_TEXT("주관식", false),
    LONG_TEXT("장문형", false),
    MULTIPLE_CHOICE("객관식", true),
    CHECK_BOX("체크박스", true),
    FILE_UPLOAD("파일 업로드", false);

    private final String description;
    private final boolean optionRequired;

    public boolean isOptionRequired() {
        return optionRequired;
    }
}
