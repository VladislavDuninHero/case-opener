package com.case_opener_game.case_opener.service.bet.manager;

import com.case_opener_game.case_opener.dto.bet.BetDTO;
import com.case_opener_game.case_opener.dto.bet.CalculatedRoundDTO;

public interface BetManager {
    CalculatedRoundDTO runCalculate(BetDTO betDTO);
}
