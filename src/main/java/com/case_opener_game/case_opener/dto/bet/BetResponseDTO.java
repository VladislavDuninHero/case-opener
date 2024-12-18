package com.case_opener_game.case_opener.dto.bet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class BetResponseDTO {
    private Long id;
    private BigDecimal amount;
    private int multiplier;
    private BigDecimal payout;
}
