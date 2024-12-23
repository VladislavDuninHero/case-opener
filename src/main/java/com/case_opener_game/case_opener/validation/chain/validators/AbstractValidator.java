package com.case_opener_game.case_opener.validation.chain.validators;

import lombok.Setter;

@Setter
public abstract class AbstractValidator<D, V> implements Validator<D, V> {
    public AbstractValidator<D, V> next;
}
