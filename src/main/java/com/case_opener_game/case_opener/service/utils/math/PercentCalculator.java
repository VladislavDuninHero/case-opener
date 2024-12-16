package com.case_opener_game.case_opener.service.utils.math;

import com.case_opener_game.case_opener.dto.multiplier.Multiplier;

import java.util.List;

public interface PercentCalculator {
    List<Multiplier> calculateCumulativeValue(List<Integer> multipliers);
}
