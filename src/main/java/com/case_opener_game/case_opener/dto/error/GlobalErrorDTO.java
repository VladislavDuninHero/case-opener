package com.case_opener_game.case_opener.dto.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalErrorDTO extends AbstractError {
    public GlobalErrorDTO(String errorCode, String message) {
        super(errorCode, message);
    }
}
