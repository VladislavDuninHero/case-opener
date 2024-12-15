package com.case_opener_game.case_opener.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ValidationErrorDTO {
    private List<ErrorDTO> errors;
}
