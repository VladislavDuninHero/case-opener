package com.case_opener_game.case_opener.dto.bootstrap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BootstrapDTO {

    private String game;

    private List<Double> multipliers;

    private String difficulty;

    private BigDecimal balance;
}
