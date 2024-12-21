package com.case_opener_game.case_opener.validation.chain;

import com.case_opener_game.case_opener.config.ValidatorsConfig;
import com.case_opener_game.case_opener.dto.user.BalanceDTO;
import org.springframework.stereotype.Service;

@Service
public class BalanceValidationServiceImpl implements ValidationService<BalanceDTO> {

    private final ValidatorsConfig validatorsConfig;

    public BalanceValidationServiceImpl(ValidatorsConfig validatorsConfig) {
        this.validatorsConfig = validatorsConfig;
    }

    @Override
    public void validate(BalanceDTO value) {
        validatorsConfig.createBalanceValidatorsChain().forEach(validator -> validator.validate(value));
    }
}
