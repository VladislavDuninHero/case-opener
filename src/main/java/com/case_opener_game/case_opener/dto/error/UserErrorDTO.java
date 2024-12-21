package com.case_opener_game.case_opener.dto.error;

public class UserErrorDTO extends AbstractError {
    public UserErrorDTO(String errorCode, String message) {
        super(errorCode, message);
    }
}
