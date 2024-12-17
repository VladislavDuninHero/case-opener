package com.case_opener_game.case_opener.service.bet.factory;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Map;

@Getter
@Service
public class MultiplierPercentageFactory {
    private final Map<Integer, Double> percentages = Map.ofEntries(
            Map.entry(0, 60.0),
            Map.entry(1, 50.0),
            Map.entry(2, 20.0),
            Map.entry(5, 10.0),
            Map.entry(10, 5.0),
            Map.entry(20, 2.0),
            Map.entry(30, 1.0),
            Map.entry(40, 0.8),
            Map.entry(60, 0.5),
            Map.entry(80, 0.2),
            Map.entry(120, 0.1)
    );

    public Double getPercentage(Integer percent) {
        if (!percentages.containsKey(percent)) {
            throw new IllegalArgumentException("Percentage is not implemented");
        }

        return percentages.get(percent);
    }
}
