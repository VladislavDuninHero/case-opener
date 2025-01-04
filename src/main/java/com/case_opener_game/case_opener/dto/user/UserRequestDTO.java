package com.case_opener_game.case_opener.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank
    @NotNull
    @Size(min = 1, max = 20)
    private String login;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 255)
    private String password;
}
