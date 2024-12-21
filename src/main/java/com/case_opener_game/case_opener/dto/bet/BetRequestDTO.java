package com.case_opener_game.case_opener.dto.bet;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class BetRequestDTO {

    @NotNull
    @PositiveOrZero
    private BigDecimal amount;
}
