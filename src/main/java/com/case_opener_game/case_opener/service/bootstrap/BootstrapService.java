package com.case_opener_game.case_opener.service.bootstrap;

import com.case_opener_game.case_opener.dto.bootstrap.BootstrapDTO;
import com.case_opener_game.case_opener.dto.GameDTO;
import com.case_opener_game.case_opener.dto.user.UserDTO;

public interface BootstrapService {
    BootstrapDTO bootstrap(GameDTO gameDTO, UserDTO userDTO);
}
