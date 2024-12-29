package com.case_opener_game.case_opener.repository;

import com.case_opener_game.case_opener.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
