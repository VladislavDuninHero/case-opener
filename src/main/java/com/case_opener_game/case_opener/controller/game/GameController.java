package com.case_opener_game.case_opener.controller.game;

import com.case_opener_game.case_opener.constants.Routes;
import com.case_opener_game.case_opener.dto.bet.BetDTO;
import com.case_opener_game.case_opener.dto.bet.BetRequestDTO;
import com.case_opener_game.case_opener.dto.bet.BetResponseDTO;
import com.case_opener_game.case_opener.dto.bootstrap.BootstrapDTO;
import com.case_opener_game.case_opener.dto.GameDTO;
import com.case_opener_game.case_opener.dto.user.BalanceDTO;
import com.case_opener_game.case_opener.dto.user.UserInfoDTO;
import com.case_opener_game.case_opener.exception.game.LowBalanceException;
import com.case_opener_game.case_opener.service.bet.BetService;
import com.case_opener_game.case_opener.service.bootstrap.BootstrapService;
import com.case_opener_game.case_opener.validation.chain.BalanceValidationServiceImpl;
import com.case_opener_game.case_opener.validation.chain.ValidationService;
import com.case_opener_game.case_opener.validation.chain.validators.impl.LowBalanceValidator;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class GameController {

    private final BootstrapService bootstrapService;
    private final BetService betService;
    private final BalanceValidationServiceImpl balanceValidationService;

    public GameController(
            BootstrapService bootstrapService,
            BetService betService,
            BalanceValidationServiceImpl balanceValidationService
    ) {
        this.bootstrapService = bootstrapService;
        this.betService = betService;
        this.balanceValidationService = balanceValidationService;
    }

    @PostMapping(Routes.API_BOOTSTRAP_ROUTE)
    public ResponseEntity<BootstrapDTO> bootstrap(
            @Validated @RequestBody GameDTO gameDTO,
            HttpSession session
    ) {
        BootstrapDTO bootstrapDTO = bootstrapService.bootstrap(gameDTO);

        session.setAttribute("difficulty", gameDTO.getDifficulty());
        session.setAttribute("gameName", gameDTO.getGameName());
        session.setAttribute("balance", bootstrapDTO.getBalance());

        return ResponseEntity.ok().body(bootstrapDTO);
    }

    @PostMapping(Routes.GAME_BET_ROUTE)
    public ResponseEntity<BetResponseDTO> bet(
            @Validated @RequestBody BetRequestDTO betRequestDTO,
            HttpSession session,
            Model model,
            BindingResult bindingResult
    ) {
        String difficulty = session.getAttribute("difficulty").toString();
        String gameName = session.getAttribute("gameName").toString();
        BigDecimal balance = (BigDecimal) session.getAttribute("balance");

        balanceValidationService.validate(new BalanceDTO(balance));

        BetDTO betDTO = new BetDTO(
                betRequestDTO,
                new GameDTO(gameName, difficulty),
                new UserInfoDTO(balance)
        );


        BetResponseDTO betResponseDTO = betService.bet(betDTO);

        model.addAttribute("balance", betResponseDTO.getBalance());
        session.setAttribute("balance", betResponseDTO.getBalance());

        return ResponseEntity.ok().body(betResponseDTO);
    }

    @GetMapping("api/v1/game/balance")
    public ResponseEntity<BalanceDTO> getBalance(HttpSession session) {
        BigDecimal balance = (BigDecimal) session.getAttribute("balance");

        BalanceDTO balanceDTO = new BalanceDTO(
                balance
        );

        return ResponseEntity.ok().body(balanceDTO);
    }
}
