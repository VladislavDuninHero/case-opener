package com.case_opener_game.case_opener.controller.error;

import com.case_opener_game.case_opener.dto.error.GlobalErrorDTO;
import com.case_opener_game.case_opener.dto.error.ValidationErrorDTO;
import com.case_opener_game.case_opener.enums.ErrorCode;
import com.case_opener_game.case_opener.exception.session.SessionInitException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;


import java.util.List;

@RestControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(SessionInitException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorDTO<GlobalErrorDTO> onSessionInitException(SessionInitException ex) {
        List<GlobalErrorDTO> errors = List.of(
                new GlobalErrorDTO(
                        ErrorCode.NOT_FOUND.getCode(),
                        ex.getMessage()
                )
        );

        return new ValidationErrorDTO<>(errors);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ValidationErrorDTO<GlobalErrorDTO> onNoResourceFoundException(NoResourceFoundException ex) {
        List<GlobalErrorDTO> errors = List.of(new GlobalErrorDTO(
                ErrorCode.NOT_FOUND.getCode(),
                ex.getMessage()
        ));

        return new ValidationErrorDTO<>(errors);
    }
}
