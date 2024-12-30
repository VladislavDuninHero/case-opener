package com.case_opener_game.case_opener.dto.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class WalletDTO {
    private Long id;
    private BigDecimal balance;
}
