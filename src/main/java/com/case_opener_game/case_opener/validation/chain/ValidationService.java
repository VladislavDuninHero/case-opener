package com.case_opener_game.case_opener.validation.chain;


public interface ValidationService<V, E> {

    public void validate(V value, E extension);
}
