package com.case_opener_game.case_opener.validation.chain;

import com.case_opener_game.case_opener.config.ValidatorsConfig;
import com.case_opener_game.case_opener.dto.bet.BetRequestDTO;
import com.case_opener_game.case_opener.dto.user.BalanceDTO;
import org.springframework.stereotype.Service;

@Service
public class BalanceValidationServiceImpl implements ValidationService<BalanceDTO, BetRequestDTO> {

    private final ValidatorsConfig validatorsConfig;

    public BalanceValidationServiceImpl(ValidatorsConfig validatorsConfig) {
        this.validatorsConfig = validatorsConfig;
    }

    @Override
    public void validate(BalanceDTO value, BetRequestDTO request) {
        validatorsConfig.createBalanceValidatorsChain().forEach(validator -> validator.validate(value, request));
    }

}
