package com.case_opener_game.case_opener.service.bet.manager.balance.impl;

import com.case_opener_game.case_opener.dto.user.BalanceDTO;
import com.case_opener_game.case_opener.service.bet.manager.balance.BalanceManager;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BalanceManagerImpl implements BalanceManager {

    @Override
    public BalanceDTO calculateBalance(BigDecimal balance, BigDecimal amount, BigDecimal payout) {
        BigDecimal balanceAfterBet = balance.subtract(amount);
        BigDecimal balanceAfterPayout = balanceAfterBet.add(payout);

        return new BalanceDTO(balanceAfterPayout);
    }
}
