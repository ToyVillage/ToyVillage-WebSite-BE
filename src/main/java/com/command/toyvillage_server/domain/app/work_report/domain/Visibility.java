package com.command.toyvillage_server.domain.app.work_report.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Visibility {
    ALL("전체 공개"),
    TEAM("팀 공개"),
    PRIVATE("작성자만 공개");

    private final String description;
}
