package com.case_opener_game.case_opener.service.bet.manager.balance;

import com.case_opener_game.case_opener.dto.user.BalanceDTO;

import java.math.BigDecimal;

public interface BalanceManager {
    public BalanceDTO calculateBalance(
            BigDecimal balance,
            BigDecimal amount,
            BigDecimal payout
    );
}
