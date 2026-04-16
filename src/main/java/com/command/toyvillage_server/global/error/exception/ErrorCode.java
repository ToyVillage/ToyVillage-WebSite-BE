package com.command.toyvillage_server.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    METHOD_NOT_ALLOWED(405, "지원하지 않는 메서드 형식입니다."),

    // popup
    POPUP_NOT_FOUND(404, "존재하지 않는 팝업입니다."),

    // auth
    LOGIN_INFO_NOT_MATCHED(401, "아이디 또는 비밀번호를 확인해주세요"),
    EMAIL_NOT_MATCHED(400, "요청의 이메일이 인증된 사용자의 이메일과 일치하지 않습니다."),
    VERIFICATION_CODE_EXPIRED(404, "인증 코드가 만료되었습니다."),
    VERIFICATION_CODE_NOT_MATCHED(400, "인증 코드가 일치하지 않습니다."),
    RESET_TOKEN_NOT_FOUND(401, "유효하지 않은 비밀번호 재설정 토큰 입니다."),
    MANY_REQUEST(429, "5분 뒤에 다시 시도해주세요."),

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
    INTERNAL_SERVER_ERROR(500, "내부 서버 오류가 발생했습니다."),

    //aws s3
    FILE_EMPTY(400, "파일이 비어있습니다."),
    KEY_EMPTY(400, "해당 파일의 키가 비어있습니다."),
    FILE_UPLOAD_FAIL(500, "파일을 업로드하는중 문제가 발생했습니다."),
    FILE_DELETE_FAIL(500, "파일을 삭제하던중 문제가 발생했습니다."),
    FILE_DELETE_FAIL(500, "파일을 삭제하던 중 문제가 발생했습니다."),

    //partnership
    PARTNERSHIP_NOT_FOUND(404, "해당 제휴문의를 찾을수 없습니다.");

    //file
    FILE_NOT_FOUND(404, "파일을 찾을 수 없습니다."),

    //gallery
    GALLERY_NOT_FOUND(404, "존재하지 않는 갤러리입니다.");

    private final int statusCode;
    private final String errorMessage;
}
