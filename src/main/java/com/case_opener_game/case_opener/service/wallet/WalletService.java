package com.case_opener_game.case_opener.service.wallet;

import com.case_opener_game.case_opener.dto.wallet.WalletDTO;
import com.case_opener_game.case_opener.entity.Wallet;

import java.math.BigDecimal;

public interface WalletService {
    Wallet createWallet(WalletDTO walletDTO);
    WalletDTO getWalletById(Long id);
    void updateWalletBalanceById(BigDecimal value, Long id);
}
