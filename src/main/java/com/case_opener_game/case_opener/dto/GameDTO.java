package com.case_opener_game.case_opener.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    @NotNull
    private String gameName;

    @NotBlank
    @NotEmpty
    @NotNull
    private String difficulty;
}
