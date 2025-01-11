package com.case_opener_game.case_opener.repository;

import com.case_opener_game.case_opener.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet getWalletById(Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE Wallet w SET w.balance = :balance WHERE w.id = :id")
    void updateWalletBalanceById(BigDecimal balance, Long id);
}
