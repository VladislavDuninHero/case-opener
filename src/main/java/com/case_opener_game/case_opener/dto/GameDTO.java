package com.case_opener_game.case_opener.dto;

import com.case_opener_game.case_opener.constants.ExceptionMessage;
import com.case_opener_game.case_opener.enums.GameDifficulty;
import com.case_opener_game.case_opener.validation.annotations.EnumValidate;
import com.case_opener_game.case_opener.enums.Game;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@Validated
public class GameDTO {

    @NotBlank
    @NotNull
    @EnumValidate(enumClass = Game.class, message = ExceptionMessage.GAME_NOT_FOUND_EXCEPTION)
    private String gameName;

    @NotBlank
    @NotNull
    @EnumValidate(enumClass = GameDifficulty.class, message = ExceptionMessage.DIFFICULTY_NOT_FOUND_EXCEPTION)
    private String difficulty;
}
