package com.case_opener_game.case_opener.dto.user;

import com.case_opener_game.case_opener.dto.wallet.WalletDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private UserResponseDTO user;
    private WalletDTO wallet;
}
