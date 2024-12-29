package com.case_opener_game.case_opener.controller.user;

import com.case_opener_game.case_opener.constants.Routes;
import com.case_opener_game.case_opener.dto.user.UserDTO;
import com.case_opener_game.case_opener.dto.user.UserResponseDTO;
import com.case_opener_game.case_opener.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(Routes.API_USER_CREATE_ROUTE)
    public ResponseEntity<UserResponseDTO> createUser(@Validated @RequestBody UserDTO userDTO) {

        UserResponseDTO createdUser = userService.createUser(userDTO);

        return ResponseEntity
                .created(URI.create(Routes.API_USER_CREATE_ROUTE))
                .body(createdUser);
    }
}
