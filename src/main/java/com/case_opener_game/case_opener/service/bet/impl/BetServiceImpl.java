package com.case_opener_game.case_opener.service.bet.impl;

import com.case_opener_game.case_opener.dto.bet.BetRequestDTO;
import com.case_opener_game.case_opener.dto.bet.BetResponseDTO;
import com.case_opener_game.case_opener.repository.BetRepository;
import com.case_opener_game.case_opener.service.bet.BetService;
import org.springframework.stereotype.Service;

@Service
public class BetServiceImpl implements BetService {

    private final BetRepository betRepository;

    public BetServiceImpl(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    @Override
    public BetResponseDTO bet(BetRequestDTO betRequestDTO) {


        return null;
    }
}
