package com.case_opener_game.case_opener.service.bet;

import com.case_opener_game.case_opener.dto.bet.BetDTO;
import com.case_opener_game.case_opener.dto.bet.BetRequestDTO;
import com.case_opener_game.case_opener.dto.bet.BetResponseDTO;
import com.case_opener_game.case_opener.enums.GameDifficulty;

public interface BetService {
    public BetResponseDTO bet(BetDTO betDTO);
}
