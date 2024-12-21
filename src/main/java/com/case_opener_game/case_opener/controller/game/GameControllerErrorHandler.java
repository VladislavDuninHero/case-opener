package com.case_opener_game.case_opener.controller.game;

import com.case_opener_game.case_opener.dto.error.ErrorDTO;
import com.case_opener_game.case_opener.dto.error.ValidationErrorDTO;
import com.case_opener_game.case_opener.enums.ErrorCode;
import com.case_opener_game.case_opener.exception.game.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice(basePackageClasses = GameController.class)
public class GameControllerErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorDTO<ErrorDTO> onValidationException(MethodArgumentNotValidException ex) {
        final List<ErrorDTO> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> new ErrorDTO(
                                error.getField(),
                                error.getDefaultMessage(),
                                ErrorCode.VALIDATION_FAILED.getCode()
                            )
                )
                .toList();

        return new ValidationErrorDTO<>(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorDTO<ErrorDTO> onIllegalArgumentException(IllegalArgumentException ex) {
        final List<ErrorDTO> errors = List.of(
                new ErrorDTO(
                        ex.getLocalizedMessage(),
                        ex.getMessage(),
                        ErrorCode.NOT_FOUND.getCode()
                )
        );

        return new ValidationErrorDTO<>(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorDTO<ErrorDTO> onConstraintViolationException(HttpMessageNotReadableException ex) {
        final List<ErrorDTO> errors = List.of(
                new ErrorDTO(
                        ex.getMessage(),
                        ex.getLocalizedMessage(),
                        ErrorCode.NOT_FOUND.getCode()
                )
        );

        return new ValidationErrorDTO<>(errors);
    }

    @ExceptionHandler(NotImplementedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorDTO<ErrorDTO> onNotImplementedException(NotImplementedException ex) {
        final List<ErrorDTO> errors = List.of(
                new ErrorDTO(
                        ex.getMessage(),
                        ex.getLocalizedMessage(),
                        ErrorCode.NOT_IMPLEMENTED.getCode()
                )
        );

        return new ValidationErrorDTO<>(errors);
    }
}
