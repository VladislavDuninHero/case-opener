package com.case_opener_game.case_opener.config;

import com.case_opener_game.case_opener.enums.GameDifficulty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class BootstrapFactoryBean {

    @Bean
    public Map<GameDifficulty, List<Integer>> multipliers() {
        return Map.ofEntries(
                Map.entry(
                        GameDifficulty.EASY, List.of(
                                0, 1, 2, 5, 10, 20, 40, 60
                        )
                ),
                Map.entry(
                        GameDifficulty.MEDIUM, List.of(
                            0, 0, 1, 2, 5, 10, 20, 40, 60, 80
                        )
                ),
                Map.entry(
                        GameDifficulty.HARD, List.of(
                                0, 0, 0, 1, 2, 5, 10, 20, 40, 60, 80, 120
                        )
                )
        );
    }

}
