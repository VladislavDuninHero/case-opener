package com.case_opener_game.case_opener.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NOT_FOUND("0001"),
    SESSION_NOT_FOUND("0002"),
    NOT_IMPLEMENTED("0003"),
    VALIDATION_FAILED("0004"),
    LOW_BALANCE("0005");

    private String code;

    private ErrorCode(String code) {
        this.code = code;
    }
}
