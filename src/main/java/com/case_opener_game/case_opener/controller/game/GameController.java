package com.case_opener_game.case_opener.controller.game;

import com.case_opener_game.case_opener.dto.bet.BetRequestDTO;
import com.case_opener_game.case_opener.dto.bet.BetResponseDTO;
import com.case_opener_game.case_opener.dto.bootstrap.BootstrapDTO;
import com.case_opener_game.case_opener.dto.GameDTO;
import com.case_opener_game.case_opener.service.bet.BetService;
import com.case_opener_game.case_opener.service.bootstrap.BootstrapService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class GameController {

    private final BootstrapService bootstrapService;
    private final BetService betService;

    public GameController(
            BootstrapService bootstrapService,
            BetService betService
    ) {
        this.bootstrapService = bootstrapService;
        this.betService = betService;
    }

    @PostMapping("/bootstrap")
    public ResponseEntity<BootstrapDTO> bootstrap(@Validated @RequestBody GameDTO gameDTO) {
        BootstrapDTO bootstrapDTO = bootstrapService.bootstrap(gameDTO);

        return ResponseEntity.ok().body(bootstrapDTO);
    }

    @PostMapping("/bet")
    public ResponseEntity<BetResponseDTO> bet(@RequestBody BetRequestDTO betDTO) {
        BetResponseDTO betResponseDTO = betService.bet(betDTO);

        return ResponseEntity.ok().body(betResponseDTO);
    }
}
