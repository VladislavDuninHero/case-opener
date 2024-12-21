package com.case_opener_game.case_opener.dto.bet;

import com.case_opener_game.case_opener.dto.GameDTO;
import com.case_opener_game.case_opener.dto.user.UserInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BetDTO {
    private BetRequestDTO betInfo;
    private GameDTO sessionInfo;
    private UserInfoDTO userInfo;
}
