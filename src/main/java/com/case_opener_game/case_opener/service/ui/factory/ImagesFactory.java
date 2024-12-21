package com.case_opener_game.case_opener.service.ui.factory;

import com.case_opener_game.case_opener.dto.ui.Image;
import com.case_opener_game.case_opener.enums.GameDifficulty;

import java.util.List;

public interface ImagesFactory {
    List<Image> getImages(GameDifficulty gameDifficulty);
}
