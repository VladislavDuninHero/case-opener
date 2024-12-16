package com.case_opener_game.case_opener.service.bet.factory;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MultiplierPercentageFactory {
    private final Map<Integer, Double> percentages = Map.ofEntries(
            Map.entry(0, 40.0),
            Map.entry(1, 40.0),
            Map.entry(2, 30.0),
            Map.entry(5, 30.0),
            Map.entry(10, 20.0),
            Map.entry(20, 20.0),
            Map.entry(30, 10.0),
            Map.entry(40, 10.0),
            Map.entry(60, 5.0),
            Map.entry(80, 5.0),
            Map.entry(120, 0.5)
    );

    public Double getPercentage(Integer percent) {
        if (!percentages.containsKey(percent)) {
            throw new IllegalArgumentException("Percentage is not implemented");
        }

        return percentages.get(percent);
    }
}
