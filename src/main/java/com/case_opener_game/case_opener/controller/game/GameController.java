package com.case_opener_game.case_opener.controller.game;

import com.case_opener_game.case_opener.constants.DemoAccounts;
import com.case_opener_game.case_opener.constants.Routes;
import com.case_opener_game.case_opener.constants.SessionAttributes;
import com.case_opener_game.case_opener.dto.bet.BetDTO;
import com.case_opener_game.case_opener.dto.bet.BetRequestDTO;
import com.case_opener_game.case_opener.dto.bet.BetResponseDTO;
import com.case_opener_game.case_opener.dto.bootstrap.BootstrapDTO;
import com.case_opener_game.case_opener.dto.GameDTO;
import com.case_opener_game.case_opener.dto.user.BalanceDTO;
import com.case_opener_game.case_opener.dto.user.UserDTO;
import com.case_opener_game.case_opener.dto.user.UserInfoDTO;
import com.case_opener_game.case_opener.service.bet.BetService;
import com.case_opener_game.case_opener.service.bootstrap.BootstrapService;
import com.case_opener_game.case_opener.service.user.UserService;
import com.case_opener_game.case_opener.service.wallet.WalletService;
import com.case_opener_game.case_opener.validation.chain.BalanceValidationServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class GameController {

    private final BootstrapService bootstrapService;
    private final BetService betService;
    private final BalanceValidationServiceImpl balanceValidationService;
    private final UserService userService;
    private final WalletService walletService;

    public GameController(
            BootstrapService bootstrapService,
            BetService betService,
            BalanceValidationServiceImpl balanceValidationService,
            UserService userService,
            WalletService walletService
    ) {
        this.bootstrapService = bootstrapService;
        this.betService = betService;
        this.balanceValidationService = balanceValidationService;
        this.userService = userService;
        this.walletService = walletService;
    }

    @PostMapping(Routes.API_BOOTSTRAP_ROUTE)
    public ResponseEntity<BootstrapDTO> bootstrap(
            @Validated @RequestBody GameDTO gameDTO,
            HttpSession session
    ) {
        UserDTO userDTO = userService.getUserByLogin(DemoAccounts.DEMO_ACCOUNT_LOGIN);
        BootstrapDTO bootstrapDTO = bootstrapService.bootstrap(gameDTO, userDTO);

        session.setAttribute(SessionAttributes.DIFFICULTY, gameDTO.getDifficulty());
        session.setAttribute(SessionAttributes.GAME_NAME, gameDTO.getGameName());
        session.setAttribute(SessionAttributes.BALANCE, userDTO.getWallet().getBalance());
        session.setAttribute(SessionAttributes.WALLET_ID, userDTO.getWallet().getId());

        return ResponseEntity.ok().body(bootstrapDTO);
    }

    @PostMapping(Routes.GAME_BET_ROUTE)
    public ResponseEntity<BetResponseDTO> bet(
            @Validated @RequestBody BetRequestDTO betRequestDTO,
            HttpSession session,
            Model model
    ) {
        String difficulty = session.getAttribute(SessionAttributes.DIFFICULTY).toString();
        String gameName = session.getAttribute(SessionAttributes.GAME_NAME).toString();
        BigDecimal balance = (BigDecimal) session.getAttribute(SessionAttributes.BALANCE);

        balanceValidationService.validate(new BalanceDTO(balance), betRequestDTO);

        BetDTO betDTO = new BetDTO(
                betRequestDTO,
                new GameDTO(gameName, difficulty),
                new UserInfoDTO(balance)
        );

        BetResponseDTO betResponseDTO = betService.bet(betDTO);
        walletService.updateWalletBalanceById(
                betResponseDTO.getBalance(),
                (Long) session.getAttribute(SessionAttributes.WALLET_ID)
        );

        model.addAttribute(SessionAttributes.BALANCE, betResponseDTO.getBalance());
        session.setAttribute(SessionAttributes.BALANCE, betResponseDTO.getBalance());

        return ResponseEntity.ok().body(betResponseDTO);
    }

    @GetMapping(Routes.API_BALANCE_ROUTE)
    public ResponseEntity<BalanceDTO> getBalance(HttpSession session) {
        BigDecimal balance = (BigDecimal) session.getAttribute(SessionAttributes.BALANCE);

        BalanceDTO balanceDTO = new BalanceDTO(
                balance
        );

        return ResponseEntity.ok().body(balanceDTO);
    }
}
