package com.case_opener_game.case_opener.service.bet.impl;

import com.case_opener_game.case_opener.dto.bet.BetDTO;
import com.case_opener_game.case_opener.dto.bet.BetRequestDTO;
import com.case_opener_game.case_opener.dto.bet.BetResponseDTO;
import com.case_opener_game.case_opener.dto.bet.CalculatedRoundDTO;
import com.case_opener_game.case_opener.entity.Round;
import com.case_opener_game.case_opener.repository.BetRepository;
import com.case_opener_game.case_opener.service.bet.BetService;
import com.case_opener_game.case_opener.service.bet.manager.BetManager;
import com.case_opener_game.case_opener.service.utils.mapping.RoundMapper;
import org.springframework.stereotype.Service;

@Service
public class BetServiceImpl implements BetService {

    private final BetRepository betRepository;
    private final RoundMapper roundMapper;
    private final BetManager betManager;

    public BetServiceImpl(
            BetRepository betRepository,
            RoundMapper roundMapper,
            BetManager betManager
    ) {
        this.betRepository = betRepository;
        this.roundMapper = roundMapper;
        this.betManager = betManager;
    }

    @Override
    public BetResponseDTO bet(BetDTO betDTO) {
        CalculatedRoundDTO calculatedRound = betManager.runCalculate(betDTO);

        Round roundEntity = roundMapper.toEntity(calculatedRound);

        Round round = betRepository.save(roundEntity);

        return roundMapper.toDto(round);
    }
}
