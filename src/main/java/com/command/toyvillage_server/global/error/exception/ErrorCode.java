package com.command.toyvillage_server.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //general
    BAD_REQUEST(400, "잘못된 요청입니다."),
    NOT_FOUND(404, "존재하지 않는 페이지 입니다."),
    INTERNAL_SERVER_ERROR(500, "예상하지 못한 에러가 발생했습니다.");

    private final int statusCode;
    private final String errorMessage;
}
