package com.case_opener_game.case_opener.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String login;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
