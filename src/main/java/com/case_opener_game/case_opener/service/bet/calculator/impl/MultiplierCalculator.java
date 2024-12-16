package com.case_opener_game.case_opener.service.bet.calculator.impl;

import com.case_opener_game.case_opener.config.BootstrapFactoryBean;
import com.case_opener_game.case_opener.dto.bet.BetDTO;
import com.case_opener_game.case_opener.enums.CalculatorType;
import com.case_opener_game.case_opener.enums.GameDifficulty;
import com.case_opener_game.case_opener.service.bet.calculator.Calculator;
import com.case_opener_game.case_opener.service.utils.math.PercentCalculator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MultiplierCalculator implements Calculator<Integer, BetDTO> {

    private final BootstrapFactoryBean bootstrapFactoryBean;
    private final PercentCalculator percentCalculator;

    public MultiplierCalculator(BootstrapFactoryBean bootstrapFactoryBean, PercentCalculator percentCalculator) {
        this.bootstrapFactoryBean = bootstrapFactoryBean;
        this.percentCalculator = percentCalculator;
    }

    @Override
    public Integer calculate(BetDTO round) {
        String difficulty = round.getSessionInfo().getDifficulty();
        List<Integer> multipliers = bootstrapFactoryBean.multipliers().get(difficulty);



        return 0;
    }

    @Override
    public CalculatorType getType() {
        return CalculatorType.MULTIPLIER_CALCULATOR;
    }

    private Integer getRandomMultiplier(List<Integer> multipliers) {
        return 0;

    }
}
