package com.case_opener_game.case_opener.validation.chain.validators;


public interface Validator<D, V> {
    public D validate(D value, V extension);
}
