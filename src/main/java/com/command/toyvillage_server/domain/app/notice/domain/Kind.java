package com.command.toyvillage_server.domain.app.notice.domain;

import lombok.Getter;

public enum Kind {
    ALL("전체");

    @Getter
    private final String kindName;

    Kind(String kindName) {
        this.kindName = kindName;
    }
}
