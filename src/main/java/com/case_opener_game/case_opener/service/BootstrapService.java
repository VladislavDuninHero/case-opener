package com.case_opener_game.case_opener.service;

import com.case_opener_game.case_opener.dto.BootstrapDTO;
import org.springframework.stereotype.Service;

@Service
public class BootstrapService {

    public BootstrapDTO bootstrap() {
        return new BootstrapDTO();
    }
}
