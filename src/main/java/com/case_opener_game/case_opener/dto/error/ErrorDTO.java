package com.case_opener_game.case_opener.dto.error;

import lombok.*;

@Getter
@Setter
public class ErrorDTO extends AbstractError {
    private String field;

    public ErrorDTO(String field, String message, String errorCode) {
        super(errorCode, message);
        this.field = field;
    }
}
