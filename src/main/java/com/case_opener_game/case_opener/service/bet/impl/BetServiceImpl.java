package com.case_opener_game.case_opener.service.bet.impl;

import com.case_opener_game.case_opener.dto.bet.BetDTO;
import com.case_opener_game.case_opener.dto.bet.BetResponseDTO;
import com.case_opener_game.case_opener.dto.bet.CalculatedRoundDTO;
import com.case_opener_game.case_opener.dto.user.BalanceDTO;
import com.case_opener_game.case_opener.entity.Round;
import com.case_opener_game.case_opener.repository.BetRepository;
import com.case_opener_game.case_opener.service.bet.BetService;
import com.case_opener_game.case_opener.service.bet.manager.balance.BalanceManager;
import com.case_opener_game.case_opener.service.bet.manager.bet.BetManager;
import com.case_opener_game.case_opener.service.utils.mapping.RoundMapper;
import com.case_opener_game.case_opener.service.wallet.WalletService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BetServiceImpl implements BetService {

    private final BetRepository betRepository;
    private final RoundMapper roundMapper;
    private final BetManager betManager;
    private final BalanceManager balanceManager;
    private final WalletService walletService;

    public BetServiceImpl(
            BetRepository betRepository,
            RoundMapper roundMapper,
            BetManager betManager,
            BalanceManager balanceManager,
            WalletService walletService
    ) {
        this.betRepository = betRepository;
        this.roundMapper = roundMapper;
        this.betManager = betManager;
        this.balanceManager = balanceManager;
        this.walletService = walletService;
    }

    @Override
    public BetResponseDTO bet(BetDTO betDTO) {
        CalculatedRoundDTO calculatedRound = betManager.runCalculate(betDTO);
        BalanceDTO calculatedBalance = balanceManager.calculateBalance(
                betDTO.getUserInfo().getBalance(),
                betDTO.getBetInfo().getAmount(),
                calculatedRound.getPayout()
        );

        Round roundEntity = roundMapper.toEntity(calculatedRound);

        Round round = betRepository.save(roundEntity);

        BetResponseDTO betResponseDTO = roundMapper.toDto(round);
        betResponseDTO.setBalance(calculatedBalance.getBalance());

        return betResponseDTO;
    }
}
