package com.case_opener_game.case_opener.controller.game;

import com.case_opener_game.case_opener.dto.BootstrapDTO;
import com.case_opener_game.case_opener.dto.GameDTO;
import com.case_opener_game.case_opener.service.BootstrapService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class GameController {

    private final BootstrapService bootstrapService;

    public GameController(BootstrapService bootstrapService) {
        this.bootstrapService = bootstrapService;
    }

    @GetMapping("/bootstrap")
    public ResponseEntity<BootstrapDTO> bootstrap(@Validated @RequestBody GameDTO gameDTO) {
        BootstrapDTO bootstrapDTO = bootstrapService.bootstrap(gameDTO);

        return ResponseEntity.ok().body(bootstrapDTO);
    }
}
