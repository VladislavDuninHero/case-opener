package com.case_opener_game.case_opener.service.wallet.impl;

import com.case_opener_game.case_opener.dto.wallet.WalletDTO;
import com.case_opener_game.case_opener.entity.Wallet;
import com.case_opener_game.case_opener.repository.WalletRepository;
import com.case_opener_game.case_opener.service.utils.mapping.WalletMapper;
import com.case_opener_game.case_opener.service.wallet.WalletService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Transactional
@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    public WalletServiceImpl(
            WalletRepository walletRepository,
            WalletMapper walletMapper
    ) {
        this.walletRepository = walletRepository;
        this.walletMapper = walletMapper;
    }

    @Override
    public Wallet createWallet(WalletDTO walletDTO) {

        Wallet wallet = walletMapper.toEntity(walletDTO);

        return walletRepository.save(wallet);
    }

    @Override
    public WalletDTO getWalletById(Long id) {
        Wallet wallet = walletRepository.getWalletById(id);

        return walletMapper.toDto(wallet);
    }

    @Override
    public void updateWalletBalanceById(BigDecimal value, Long id) {
        walletRepository.updateWalletBalanceById(value, id);
    }
}
