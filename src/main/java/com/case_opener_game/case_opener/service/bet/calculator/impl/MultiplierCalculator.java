package com.case_opener_game.case_opener.service.bet.calculator.impl;

import com.case_opener_game.case_opener.config.BootstrapFactoryBean;
import com.case_opener_game.case_opener.dto.bet.BetDTO;
import com.case_opener_game.case_opener.enums.CalculatorType;
import com.case_opener_game.case_opener.enums.GameDifficulty;
import com.case_opener_game.case_opener.service.bet.calculator.Calculator;
import com.case_opener_game.case_opener.service.bet.factory.MultiplierPercentageFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class MultiplierCalculator implements Calculator<BigDecimal, BetDTO> {

    private final BootstrapFactoryBean bootstrapFactoryBean;
    private final MultiplierPercentageFactory multiplierPercentageFactory;

    public MultiplierCalculator(
            BootstrapFactoryBean bootstrapFactoryBean,
            MultiplierPercentageFactory multiplierPercentageFactory
    ) {
        this.bootstrapFactoryBean = bootstrapFactoryBean;
        this.multiplierPercentageFactory = multiplierPercentageFactory;
    }

    @Override
    public BigDecimal calculate(BetDTO round) {
        GameDifficulty difficulty = GameDifficulty.valueOf(round.getSessionInfo().getDifficulty().toUpperCase());
        List<Integer> multipliers = bootstrapFactoryBean.multipliers().get(difficulty);

        return getRandomMultiplier(multipliers);
    }

    @Override
    public CalculatorType getType() {
        return CalculatorType.MULTIPLIER_CALCULATOR;
    }

    private BigDecimal getRandomMultiplier(List<Integer> multipliers) {
        int highPercent = 100;
        double randomValue = new Random().nextDouble(highPercent);
        System.out.println(randomValue);

        Map<Integer, Double> multiplierPercent = multiplierPercentageFactory.getPercentages();
        double cumulativeValue = 0.0;
        for (Map.Entry<Integer, Double> entry : multiplierPercent.entrySet()) {
            cumulativeValue += entry.getValue();

            if (randomValue <= cumulativeValue) {
                return BigDecimal.valueOf(entry.getKey());
            }
        }

        return BigDecimal.ONE;
    }
}
