package com.case_opener_game.case_opener.calculators;


import com.case_opener_game.case_opener.config.BootstrapFactoryBean;
import com.case_opener_game.case_opener.dto.GameDTO;
import com.case_opener_game.case_opener.dto.bet.BetDTO;
import com.case_opener_game.case_opener.dto.bet.BetRequestDTO;
import com.case_opener_game.case_opener.enums.GameDifficulty;
import com.case_opener_game.case_opener.service.bet.calculator.impl.MultiplierCalculator;
import com.case_opener_game.case_opener.service.bet.factory.MultiplierPercentageFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@ExtendWith(MockitoExtension.class)
public class PercentCalculatorTest {

    @InjectMocks
    private MultiplierCalculator multiplierCalculator;

    @Mock
    private MultiplierPercentageFactory multiplierPercentageFactory;

    @Mock
    private BootstrapFactoryBean bootstrapFactoryBean;

    @Test
    public void percentageCalculatorTest() {
        //Given
        List<Integer> multipliers = List.of(
                0, 1, 2, 5, 10, 20, 40, 60
        );

        BetDTO betDTO = new BetDTO(new BetRequestDTO(BigDecimal.ONE), new GameDTO("caseOpener", "EASY"));
        //When
        Mockito.when(bootstrapFactoryBean.multipliers()).thenReturn(
                Map.ofEntries(
                        Map.entry(GameDifficulty.EASY, multipliers)
                )
        );

        //Then
        for (int i = 0; i < 100; i++) {
            System.out.println(multiplierCalculator.calculate(betDTO));
        }
        Assertions.assertNotNull(multiplierCalculator.calculate(betDTO));
    }
}
