package com.case_opener_game.case_opener.validation.chain;


public interface ValidationService<T> {

    public void validate(T value);
}
