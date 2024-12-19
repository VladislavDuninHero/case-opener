package com.case_opener_game.case_opener.dto.bet;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class BetRequestDTO {

    @NotNull
    private BigDecimal amount;
}
