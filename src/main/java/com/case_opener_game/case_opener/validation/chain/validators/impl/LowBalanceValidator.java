package com.case_opener_game.case_opener.validation.chain.validators.impl;

import com.case_opener_game.case_opener.dto.user.BalanceDTO;
import com.case_opener_game.case_opener.exception.game.LowBalanceException;
import com.case_opener_game.case_opener.validation.chain.validators.AbstractValidator;
import org.springframework.stereotype.Component;

@Component
public class LowBalanceValidator extends AbstractValidator<BalanceDTO> {

    @Override
    public BalanceDTO validate(BalanceDTO value) {
        if (value.getBalance().doubleValue() <= 0) {
            throw new LowBalanceException("Insufficient balance");
        }

        if (this.next != null) {
            return this.next.validate(value);
        }

        return value;
    }
}
