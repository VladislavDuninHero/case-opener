package com.case_opener_game.case_opener.service.bootstrap.impl;

import com.case_opener_game.case_opener.config.BootstrapFactoryBean;
import com.case_opener_game.case_opener.dto.bootstrap.BootstrapDTO;
import com.case_opener_game.case_opener.dto.GameDTO;
import com.case_opener_game.case_opener.enums.GameDifficulty;
import com.case_opener_game.case_opener.service.bootstrap.BootstrapService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class BootstrapServiceImpl implements BootstrapService {

    private final BootstrapFactoryBean bootstrapFactoryBean;

    public BootstrapServiceImpl(BootstrapFactoryBean bootstrapFactoryBean) {
        this.bootstrapFactoryBean = bootstrapFactoryBean;
    }

    public BootstrapDTO bootstrap(GameDTO gameDTO) {

        String gameName = gameDTO.getGameName();
        String difficulty = gameDTO.getDifficulty();
        List<Double> multipliers = bootstrapFactoryBean
                .multipliers()
                .get(GameDifficulty.valueOf(difficulty.toUpperCase()))
                .keySet()
                .stream()
                .sorted()
                .toList();

        return new BootstrapDTO(
                gameName,
                multipliers,
                difficulty
        );
    }
}
