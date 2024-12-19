package com.case_opener_game.case_opener.controller;

import com.case_opener_game.case_opener.dto.GameDTO;
import com.case_opener_game.case_opener.dto.bootstrap.BootstrapDTO;
import com.case_opener_game.case_opener.dto.ui.Image;
import com.case_opener_game.case_opener.enums.GameDifficulty;
import com.case_opener_game.case_opener.exception.session.SessionInitException;
import com.case_opener_game.case_opener.service.bootstrap.BootstrapService;
import com.case_opener_game.case_opener.service.factory.ImagesFactory;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


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

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/game")
    public String bootstrap(
            @Validated @RequestBody GameDTO gameDTO,
            HttpSession session,
            Model model
    ) {
        BootstrapDTO bootstrapDTO = bootstrapService.bootstrap(gameDTO);

        session.setAttribute("difficulty", gameDTO.getDifficulty());
        session.setAttribute("gameName", gameDTO.getGameName());
        model.addAttribute("gameDTO", bootstrapDTO);

        return "game";
    }

    @GetMapping("/game")
    public String game(Model model, HttpSession session) {
        if (session.getAttribute("difficulty") == null) {
            throw new SessionInitException("Session not found");
        }

        String difficulty = session.getAttribute("difficulty").toString().toUpperCase();

        List<Image> images = imagesFactory.getImages(GameDifficulty.valueOf(difficulty));

        model.addAttribute("images", images);

        return "game";
    }
}
