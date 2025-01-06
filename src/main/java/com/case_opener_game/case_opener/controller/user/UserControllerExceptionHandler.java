package com.case_opener_game.case_opener.controller.user;

import com.case_opener_game.case_opener.dto.error.ErrorDTO;
import com.case_opener_game.case_opener.dto.error.ValidationErrorDTO;
import com.case_opener_game.case_opener.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice(basePackageClasses = UserController.class)
public class UserControllerExceptionHandler {

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

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorDTO<ErrorDTO> onUsernameNotFoundException(UsernameNotFoundException ex) {
        final List<ErrorDTO> errors = List.of(
                new ErrorDTO(
                        ex.getMessage(),
                        ex.getLocalizedMessage(),
                        ErrorCode.NOT_FOUND.getCode()
                )
        );

        return new ValidationErrorDTO<>(errors);
    }

}
