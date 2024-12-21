package com.case_opener_game.case_opener.service.factory.impl;

import com.case_opener_game.case_opener.dto.ui.Image;
import com.case_opener_game.case_opener.enums.GameDifficulty;
import com.case_opener_game.case_opener.exception.game.NotImplementedException;
import com.case_opener_game.case_opener.service.factory.ImagesFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ImagesFactoryImpl implements ImagesFactory {

    private final Map<GameDifficulty, List<Image>> images = Map.ofEntries(
            Map.entry(
                    GameDifficulty.EASY, List.of(
                            new Image("/images/0x.png"),
                            new Image("/images/1x.png"),
                            new Image("/images/2x.png"),
                            new Image("/images/5x.png"),
                            new Image("/images/10x.png"),
                            new Image("/images/20x.png"),
                            new Image("/images/40x.png"),
                            new Image("/images/60x.png")
                    )
            ),
            Map.entry(
                    GameDifficulty.MEDIUM, List.of(
                            new Image("/images/0x.png"),
                            new Image("/images/0.2x.png"),
                            new Image("/images/0.5x.png"),
                            new Image("/images/1x.png"),
                            new Image("/images/2x.png"),
                            new Image("/images/5x.png"),
                            new Image("/images/10x.png"),
                            new Image("/images/20x.png"),
                            new Image("/images/40x.png"),
                            new Image("/images/60x.png"),
                            new Image("/images/80x.png")
                    )
            ),
            Map.entry(
                    GameDifficulty.HARD, List.of(
                            new Image("/images/0x.png"),
                            new Image("/images/0.2x.png"),
                            new Image("/images/0.5x.png"),
                            new Image("/images/1x.png"),
                            new Image("/images/2x.png"),
                            new Image("/images/5x.png"),
                            new Image("/images/10x.png"),
                            new Image("/images/20x.png"),
                            new Image("/images/40x.png"),
                            new Image("/images/60x.png"),
                            new Image("/images/80x.png"),
                            new Image("/images/120x.png")
                    )
            )
    );

    @Override
    public List<Image> getImages(GameDifficulty gameDifficulty) {
        if (!images.containsKey(gameDifficulty)) {
            throw new NotImplementedException("Images for this difficulty is not implemented");
        }

        return images.get(gameDifficulty);
    }

}
