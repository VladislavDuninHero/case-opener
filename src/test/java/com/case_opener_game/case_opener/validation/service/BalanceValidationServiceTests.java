package com.case_opener_game.case_opener.validation.service;

import com.case_opener_game.case_opener.config.ValidatorsConfig;
import com.case_opener_game.case_opener.dto.user.BalanceDTO;
import com.case_opener_game.case_opener.exception.game.LowBalanceException;
import com.case_opener_game.case_opener.validation.chain.BalanceValidationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class BalanceValidationServiceTests {

    @InjectMocks
    private BalanceValidationServiceImpl balanceValidationService;

    @Mock
    private ValidatorsConfig validatorsConfig;

    @Mock
    private BalanceDTO balanceDTO;

    @Test
    public void balanceValidationServiceNegativeBalanceTest() {
        //Given
        BigDecimal balance = BigDecimal.ONE.negate();
        System.out.println(balance);
        //When
        Mockito.when(balanceDTO.getBalance()).thenReturn(balance);

        //Then
        Assertions.assertThrows(LowBalanceException.class, () -> balanceValidationService.validate(balanceDTO));
    }

}
