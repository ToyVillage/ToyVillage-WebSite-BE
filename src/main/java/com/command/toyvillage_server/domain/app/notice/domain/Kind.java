package com.command.toyvillage_server.domain.app.notice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Kind {
    ALL("전체");

    @Getter
    private final String kindName;

}
