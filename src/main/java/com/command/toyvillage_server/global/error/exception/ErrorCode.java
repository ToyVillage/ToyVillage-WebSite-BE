package com.command.toyvillage_server.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    METHOD_NOT_ALLOWED(405, "지원하지 않는 메서드 형식입니다."),

    // auth
    LOGIN_INFO_NOT_MATCHED(401, "아이디 또는 비밀번호를 확인해주세요"),
    EMAIL_NOT_MATCHED(400, "요청의 이메일이 인증된 사용자의 이메일과 일치하지 않습니다."),
    VERIFICATION_CODE_EXPIRED(404, "인증 코드가 만료되었습니다."),
    VERIFICATION_CODE_NOT_MATCHED(400, "인증 코드가 일치하지 않습니다."),

    // jwt
    EXPIRED_TOKEN(401, "만료된 토큰입니다."),
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    REFRESH_TOKEN_NOT_FOUND(404, "refreshToken이 존재하지 않습니다."),

    //faq
    FAQ_NOT_FOUND(404, "존재하지 않는 질문 입니다."),
    // animal
    ANIMAL_NOT_FOUND(404, "존재하지 않는 동물소개 입니다."),

    //admin
    ADMIN_NOT_FOUND(404, "해당 관리자가 존재하지 않습니다."),
    ADMIN_MISMATCH(401, "유저가 일치하지 않습니다."),
    PASSWORD_MISMATCH(401, "비밀번호가 일치하지 않습니다."),
    ADMIN_EXIST(409, "유저가 이미 존재합니다."),

    //event
    EVENT_NOT_FOUND(404, "해당 이벤트가 존재하지 않습니다."),

    //news
    NEWS_NOT_FOUND(404, "해당 뉴스가 존재하지 않습니다."),

    // general
    BAD_REQUEST(400, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(500, "내부 서버 오류가 발생했습니다.");

    private final int statusCode;
    private final String errorMessage;
}
