package com.blog.stockandcafebe.common;

import com.blog.stockandcafebe.exception.InvalidResponseCodeException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    SUCCESS(HttpStatus.OK, 0, "OK"),

    MEMBER_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 14010, "사용자 정보에 접근할 권한이 없습니다."),
    MEMBER_UNAUTHORIZED_CREATE(HttpStatus.UNAUTHORIZED, 14011, "사용자를 추가할 수 있는 권한이 없습니다."),
    MEMBER_UNAUTHORIZED_READ(HttpStatus.UNAUTHORIZED, 14012, "사용자 정보를 확인할 수 있는 권한이 없습니다."),
    MEMBER_UNAUTHORIZED_MODIFY(HttpStatus.UNAUTHORIZED, 14013, "사용자 정보를 수정할 수 있는 권한이 없습니다."),
    MEMBER_UNAUTHORIZED_DELETE(HttpStatus.UNAUTHORIZED, 14014, "사용자를 삭제할 수 있는 권한이 없습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, 14040, "사용자를 찾을 수 없습니다."),

    ARTICLE_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 24010, "글에 접근할 권한이 없습니다."),
    ARTICLE_UNAUTHORIZED_CREATE(HttpStatus.UNAUTHORIZED, 24011, "글을 작성할 수 있는 권한이 없습니다."),
    ARTICLE_UNAUTHORIZED_READ(HttpStatus.UNAUTHORIZED, 24012, "글을 읽을 수 있는 권한이 없습니다."),
    ARTICLE_UNAUTHORIZED_MODIFY(HttpStatus.UNAUTHORIZED, 24013, "글을 수정할 수 있는 권한이 없습니다."),
    ARTICLE_UNAUTHORIZED_DELETE(HttpStatus.UNAUTHORIZED, 24014, "글을 삭제할 수 있는 권한이 없습니다."),
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, 24040, "글을 찾을 수 없습니다."),

    REPLY_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 34010, "댓글에 접근할 수 있는 권한이 없습니다."),
    REPLY_UNAUTHORIZED_CREATE(HttpStatus.UNAUTHORIZED, 34011, "댓글을 작성할 수 있는 권한이 없습니다."),
    REPLY_UNAUTHORIZED_READ(HttpStatus.UNAUTHORIZED, 34012, "댓글을 읽을 수 있는 권한이 없습니다."),
    REPLY_UNAUTHORIZED_MODIFY(HttpStatus.UNAUTHORIZED, 34013, "댓글을 수정할 수 있는 권한이 없습니다."),
    REPLY_UNAUTHORIZED_DELETE(HttpStatus.UNAUTHORIZED, 34014, "댓글을 삭제할 수 있는 권한이 없습니다."),
    REPLY_NOT_FOUND(HttpStatus.NOT_FOUND, 34040, "댓글을 찾을 수 없습니다."),

    RESPONSE_CODE_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, 99999, "확인되지 않은 오류가 발생하였습니다.");

    private final HttpStatus httpStatus;

    private final int code;

    private final String message;

    public static ResponseCode getCode(int code) {
        return Arrays.stream(values()).findFirst()
                .orElseThrow(InvalidResponseCodeException::new);
    }

}
