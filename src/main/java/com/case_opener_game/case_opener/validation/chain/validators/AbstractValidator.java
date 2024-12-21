package com.case_opener_game.case_opener.validation.chain.validators;

import lombok.Setter;

@Setter
public abstract class AbstractValidator<T> implements Validator<T> {
    public AbstractValidator<T> next;
}
