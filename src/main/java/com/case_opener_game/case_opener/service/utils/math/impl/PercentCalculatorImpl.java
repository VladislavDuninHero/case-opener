package com.case_opener_game.case_opener.service.utils.math.impl;

import com.case_opener_game.case_opener.dto.multiplier.Multiplier;
import com.case_opener_game.case_opener.service.bet.factory.MultiplierPercentageFactory;
import com.case_opener_game.case_opener.service.utils.math.PercentCalculator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PercentCalculatorImpl implements PercentCalculator {

    private final MultiplierPercentageFactory multiplierPercentageFactory;

    public PercentCalculatorImpl(MultiplierPercentageFactory multiplierPercentageFactory) {
        this.multiplierPercentageFactory = multiplierPercentageFactory;
    }

    @Override
    public List<Multiplier> calculateCumulativeValue(List<Integer> multipliers) {
        return multipliers
                .stream()
                .map(
                        multiplier -> new Multiplier(
                                multiplier,
                                multiplier + multiplierPercentageFactory.getPercentage(multiplier)
                        )
                )
                .toList();
    }
}
