package com.case_opener_game.case_opener.config;

import com.case_opener_game.case_opener.enums.GameDifficulty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class BootstrapFactoryBean {

    @Bean
    public Map<GameDifficulty, Map<Double, Double>> multipliers() {
        return Map.of(
                GameDifficulty.EASY, Map.ofEntries(
                        Map.entry(0.0, 60.0),
                        Map.entry(1.0, 50.0),
                        Map.entry(2.0, 20.0),
                        Map.entry(5.0, 10.0),
                        Map.entry(10.0, 5.0),
                        Map.entry(20.0, 2.0),
                        Map.entry(40.0, 0.8),
                        Map.entry(60.0, 0.5)
                ),
                GameDifficulty.MEDIUM, Map.ofEntries(
                        Map.entry(0.0, 60.0),
                        Map.entry(0.5, 50.0),
                        Map.entry(1.0, 20.0),
                        Map.entry(2.0, 10.0),
                        Map.entry(5.0, 5.0),
                        Map.entry(10.0, 2.0),
                        Map.entry(20.0, 1.0),
                        Map.entry(40.0, 0.8),
                        Map.entry(60.0, 0.5),
                        Map.entry(80.0, 0.2)
                ),
                GameDifficulty.HARD, Map.ofEntries(
                        Map.entry(0.0, 60.0),
                        Map.entry(0.2, 50.0),
                        Map.entry(0.5, 50.0),
                        Map.entry(1.0, 20.0),
                        Map.entry(2.0, 10.0),
                        Map.entry(5.0, 5.0),
                        Map.entry(10.0, 2.0),
                        Map.entry(20.0, 1.0),
                        Map.entry(40.0, 0.8),
                        Map.entry(60.0, 0.5),
                        Map.entry(80.0, 0.2),
                        Map.entry(120.0, 0.1)
                )
        );
    }

}
