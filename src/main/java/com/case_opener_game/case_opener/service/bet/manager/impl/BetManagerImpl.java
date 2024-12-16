package com.case_opener_game.case_opener.service.bet.manager.impl;

import com.case_opener_game.case_opener.dto.bet.BetDTO;
import com.case_opener_game.case_opener.dto.bet.CalculatedRoundDTO;
import com.case_opener_game.case_opener.enums.CalculatorType;
import com.case_opener_game.case_opener.service.bet.calculator.Calculator;
import com.case_opener_game.case_opener.service.bet.manager.BetManager;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BetManagerImpl implements BetManager {

    private final List<Calculator<BigDecimal, BetDTO>> calculators;

    public BetManagerImpl(List<Calculator<BigDecimal, BetDTO>> calculators) {
        this.calculators = calculators;
    }

    @Override
    public CalculatedRoundDTO runCalculate(BetDTO betDTO) {

        Map<String, BigDecimal> calculatedResult = calculators
                .stream()
                .collect(
                        Collectors.toMap(
                                calculator -> calculator.getType().name(),
                                calculator -> calculator.calculate(betDTO)
                        )
                );

        return compileCalculatedRound(calculatedResult);
    }

    private CalculatedRoundDTO compileCalculatedRound(Map<String, BigDecimal> calculatedResult) {
        BigDecimal multiplier = calculatedResult.get(CalculatorType.MULTIPLIER_CALCULATOR.name());

        return new CalculatedRoundDTO(
                null,
                multiplier
        );
    }
}
