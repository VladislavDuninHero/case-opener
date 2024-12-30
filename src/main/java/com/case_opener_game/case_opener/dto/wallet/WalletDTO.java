package com.case_opener_game.case_opener.dto.wallet;

import com.case_opener_game.case_opener.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class WalletDTO {
    private User userId;
    private BigDecimal balance;
}
