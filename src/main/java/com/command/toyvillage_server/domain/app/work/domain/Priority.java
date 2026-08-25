package com.command.toyvillage_server.domain.app.work.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Priority {
    HIGH("상"),
    MEDIUM("중"),
    LOW("하");

    private final String description;
}
