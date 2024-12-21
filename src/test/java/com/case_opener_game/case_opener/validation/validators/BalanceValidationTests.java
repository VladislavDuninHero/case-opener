package com.case_opener_game.case_opener.validation.validators;

import com.case_opener_game.case_opener.dto.user.BalanceDTO;
import com.case_opener_game.case_opener.exception.game.LowBalanceException;
import com.case_opener_game.case_opener.validation.chain.validators.impl.LowBalanceValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class BalanceValidationTests {

    @InjectMocks
    private LowBalanceValidator lowBalanceValidator;

    @Mock
    private BalanceDTO balanceDTO;

    @Test
    public void zeroBalanceTest() {
        //Given
        BigDecimal balance = BigDecimal.ZERO;

        //When
        Mockito.when(balanceDTO.getBalance()).thenReturn(balance);

        //Then
        Assertions.assertThrows(LowBalanceException.class, () -> lowBalanceValidator.validate(balanceDTO));
    }

    @Test
    public void negativeBalanceTest() {
        //Given
        BigDecimal balance = BigDecimal.ONE.negate();

        //When
        Mockito.when(balanceDTO.getBalance()).thenReturn(balance);

        //Then
        Assertions.assertThrows(LowBalanceException.class, () -> lowBalanceValidator.validate(balanceDTO));
    }
}
