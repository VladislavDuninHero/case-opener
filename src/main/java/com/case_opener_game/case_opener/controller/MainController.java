package com.case_opener_game.case_opener.controller;

import com.case_opener_game.case_opener.constants.ExceptionMessage;
import com.case_opener_game.case_opener.constants.Pages;
import com.case_opener_game.case_opener.constants.Routes;
import com.case_opener_game.case_opener.constants.SessionAttributes;
import com.case_opener_game.case_opener.dto.GameDTO;
import com.case_opener_game.case_opener.dto.bootstrap.BootstrapDTO;
import com.case_opener_game.case_opener.dto.ui.Image;
import com.case_opener_game.case_opener.enums.GameDifficulty;
import com.case_opener_game.case_opener.exception.session.SessionInitException;
import com.case_opener_game.case_opener.service.bootstrap.BootstrapService;
import com.case_opener_game.case_opener.service.ui.factory.ImagesFactory;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Controller
public class MainController {

    private final BootstrapService bootstrapService;
    private final ImagesFactory imagesFactory;

    public MainController(
            BootstrapService bootstrapService,
            ImagesFactory imagesFactory
    ) {
        this.bootstrapService = bootstrapService;
        this.imagesFactory = imagesFactory;
    }

    @GetMapping(Routes.HOME_ROUTE)
    public String home() {
        return Pages.HOME;
    }

    @PostMapping(Routes.GAME_ROUTE)
    public String bootstrap(
            @Validated @RequestBody GameDTO gameDTO,
            HttpSession session,
            Model model
    ) {
        BootstrapDTO bootstrapDTO = bootstrapService.bootstrap(gameDTO);

        session.setAttribute(SessionAttributes.DIFFICULTY, gameDTO.getDifficulty());
        session.setAttribute(SessionAttributes.GAME_NAME, gameDTO.getGameName());
        session.setAttribute(SessionAttributes.BALANCE, bootstrapDTO.getBalance());
        model.addAttribute(SessionAttributes.GAME_DTO, bootstrapDTO);

        return Pages.GAME;
    }

    @GetMapping(Routes.GAME_ROUTE)
    public String game(Model model, HttpSession session) {
        if (session.getAttribute(SessionAttributes.DIFFICULTY) == null) {
            throw new SessionInitException(ExceptionMessage.SESSION_NOT_FOUND_EXCEPTION);
        }

        String difficulty = session.getAttribute(SessionAttributes.DIFFICULTY).toString().toUpperCase();

        List<Image> images = imagesFactory.getImages(GameDifficulty.valueOf(difficulty));

        model.addAttribute(SessionAttributes.GAME_MULTIPLIER_IMAGES, images);
        model.addAttribute(SessionAttributes.BALANCE, session.getAttribute(SessionAttributes.BALANCE));

        return Pages.GAME;
    }
}
