package com.case_opener_game.case_opener.service.bootstrap.impl;

import com.case_opener_game.case_opener.config.BootstrapFactoryBean;
import com.case_opener_game.case_opener.dto.bootstrap.BootstrapDTO;
import com.case_opener_game.case_opener.dto.GameDTO;
import com.case_opener_game.case_opener.enums.GameDifficulty;
import com.case_opener_game.case_opener.service.bootstrap.BootstrapService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BootstrapServiceImpl implements BootstrapService {

    private final BootstrapFactoryBean bootstrapFactoryBean;

    public BootstrapServiceImpl(BootstrapFactoryBean bootstrapFactoryBean) {
        this.bootstrapFactoryBean = bootstrapFactoryBean;
    }

    public BootstrapDTO bootstrap(GameDTO gameDTO, BigDecimal balance) {

        String gameName = gameDTO.getGameName();
        String difficulty = gameDTO.getDifficulty();

        GameDifficulty gameDifficulty = GameDifficulty.valueOf(difficulty.toUpperCase());

        List<Double> multipliers = bootstrapFactoryBean
                .multipliers()
                .get(gameDifficulty)
                .keySet()
                .stream()
                .sorted()
                .toList();

        return new BootstrapDTO(
                gameName,
                multipliers,
                difficulty,
                balance
        );
    }
}
