package com.case_opener_game.case_opener.service.bet;

import com.case_opener_game.case_opener.dto.bet.BetRequestDTO;
import com.case_opener_game.case_opener.dto.bet.BetResponseDTO;

public interface BetService {
    public BetResponseDTO bet(BetRequestDTO betRequestDTO);
}
