package com.case_opener_game.case_opener.repository;

import com.case_opener_game.case_opener.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository extends JpaRepository<Round, Long> {
}
