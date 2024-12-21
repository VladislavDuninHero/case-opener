package com.case_opener_game.case_opener.validation.chain.validators;


public interface Validator<T> {
    public T validate(T value);
}
