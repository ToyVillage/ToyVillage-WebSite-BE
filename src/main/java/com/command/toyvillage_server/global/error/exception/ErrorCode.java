package com.command.toyvillage_server.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    METHOD_NOT_ALLOWED(405, "지원하지 않는 메서드 형식입니다."),

    // animal
    ANIMAL_NOT_FOUND(404, "존재하지 않는 동물소개 입니다."),

    //user
    USER_NOT_FOUND(404, "해당 유저가 존재하지 않습니다."),
    USER_MISMATCH(401, "유저가 일치하지 않습니다."),
    PASSWORD_MISMATCH(401, "비밀번호가 일치하지 않습니다."),
    USER_EXIST(409, "유저가 이미 존재합니다."),

    // general
    BAD_REQUEST(400, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(500, "내부 서버 오류가 발생했습니다.");

    private final int statusCode;
    private final String errorMessage;
}
