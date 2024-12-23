package com.case_opener_game.case_opener.validation.chain.validators.impl;

import com.case_opener_game.case_opener.constants.ExceptionMessage;
import com.case_opener_game.case_opener.dto.bet.BetRequestDTO;
import com.case_opener_game.case_opener.dto.user.BalanceDTO;
import com.case_opener_game.case_opener.exception.game.LowBalanceException;
import com.case_opener_game.case_opener.validation.chain.validators.AbstractValidator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class BetHigherThanBalanceValidator extends AbstractValidator<BalanceDTO, BetRequestDTO> {

    @Override
    public BalanceDTO validate(BalanceDTO value, BetRequestDTO request) {
        if (value.getBalance().compareTo(request.getAmount()) < 0) {
            throw new LowBalanceException(ExceptionMessage.INSUFFICIENT_BALANCE);
        }

        if (this.next != null) {
            return this.next.validate(value, request);
        }

        return value;
    }

}
