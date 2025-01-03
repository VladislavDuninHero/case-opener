package com.case_opener_game.case_opener.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoDTO {
    private BigDecimal balance;
}
