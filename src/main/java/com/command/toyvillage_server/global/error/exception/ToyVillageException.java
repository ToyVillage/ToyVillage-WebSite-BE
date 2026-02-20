package com.command.toyvillage_server.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ToyVillageException extends RuntimeException {
    private final ErrorCode errorCode;

    public ToyVillageException(ErrorCode errorCode, String customMessage) {
        super(customMessage);
        this.errorCode = errorCode;
    }
}
