package com.case_opener_game.case_opener.service.bet.calculator;

import com.case_opener_game.case_opener.enums.CalculatorType;

public interface Calculator<T, R> {
    T calculate(R round);
    CalculatorType getType();
}
