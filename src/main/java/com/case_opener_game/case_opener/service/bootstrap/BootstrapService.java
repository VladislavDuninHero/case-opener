package com.case_opener_game.case_opener.service.bootstrap;

import com.case_opener_game.case_opener.dto.bootstrap.BootstrapDTO;
import com.case_opener_game.case_opener.dto.GameDTO;

import java.math.BigDecimal;

public interface BootstrapService {
    BootstrapDTO bootstrap(GameDTO gameDTO, BigDecimal balance);
}
