package com.case_opener_game.case_opener.service.bet.calculator.impl;

import com.case_opener_game.case_opener.config.BootstrapFactoryBean;
import com.case_opener_game.case_opener.dto.bet.BetDTO;
import com.case_opener_game.case_opener.enums.CalculatorType;
import com.case_opener_game.case_opener.enums.GameDifficulty;
import com.case_opener_game.case_opener.service.bet.calculator.Calculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;

@Component
public class MultiplierCalculator implements Calculator<BigDecimal, BetDTO> {

    private final BootstrapFactoryBean bootstrapFactoryBean;

    public MultiplierCalculator(
            BootstrapFactoryBean bootstrapFactoryBean
    ) {
        this.bootstrapFactoryBean = bootstrapFactoryBean;
    }

    @Override
    public BigDecimal calculate(BetDTO round) {
        GameDifficulty difficulty = GameDifficulty.valueOf(round.getSessionInfo().getDifficulty().toUpperCase());

        return getRandomMultiplier(difficulty);
    }

    @Override
    public CalculatorType getType() {
        return CalculatorType.MULTIPLIER_CALCULATOR;
    }

    private BigDecimal getRandomMultiplier(GameDifficulty difficulty) {
        int highPercent = 100;
        double randomValue = new Random().nextDouble(highPercent);

        double cumulativeValue = 0.0;
        for (Map.Entry<Double, Double> entry : bootstrapFactoryBean.multipliers().get(difficulty).entrySet()) {
            cumulativeValue += entry.getValue();

            if (randomValue <= cumulativeValue) {
                return BigDecimal.valueOf(entry.getKey());
            }
        }

        return BigDecimal.ONE;
    }
}
