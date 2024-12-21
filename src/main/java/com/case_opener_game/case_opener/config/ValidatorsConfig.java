package com.case_opener_game.case_opener.config;

import com.case_opener_game.case_opener.dto.user.BalanceDTO;
import com.case_opener_game.case_opener.validation.chain.validators.AbstractValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ValidatorsConfig {

    private final List<AbstractValidator<BalanceDTO>> balanceValidators;

    public ValidatorsConfig(List<AbstractValidator<BalanceDTO>> balanceValidators) {
        this.balanceValidators = balanceValidators;
    }

    @Bean
    public List<AbstractValidator<BalanceDTO>> createBalanceValidatorsChain() {
        for (int i = 1; i < balanceValidators.size(); i++) {
            balanceValidators.get(i - 1).setNext(balanceValidators.get(i));
        }

        return balanceValidators;
    }
}
