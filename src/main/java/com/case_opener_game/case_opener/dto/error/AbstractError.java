package com.case_opener_game.case_opener.dto.error;

import com.case_opener_game.case_opener.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class AbstractError {
    public String errorCode;
    public String message;
}
