package com.case_opener_game.case_opener.service.wallet;

import com.case_opener_game.case_opener.dto.wallet.WalletDTO;
import com.case_opener_game.case_opener.entity.Wallet;

public interface WalletService {
    public Wallet createWallet(WalletDTO walletDTO);
}
