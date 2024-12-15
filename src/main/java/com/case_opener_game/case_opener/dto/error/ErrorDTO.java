package com.case_opener_game.case_opener.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDTO {

    private String field;
    private String message;
}
