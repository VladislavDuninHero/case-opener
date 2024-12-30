package com.case_opener_game.case_opener.controller.user;

import com.case_opener_game.case_opener.constants.Routes;
import com.case_opener_game.case_opener.dto.user.UserDTO;
import com.case_opener_game.case_opener.dto.user.UserResponseDTO;
import com.case_opener_game.case_opener.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(Routes.API_USER_ROUTE)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Validated @RequestBody UserDTO userDTO) {

        UserResponseDTO createdUser = userService.createUser(userDTO);

        return ResponseEntity
                .created(URI.create(Routes.API_USER_ROUTE))
                .body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        List<UserResponseDTO> users = userService.getUsers();

        return ResponseEntity.ok(users);
    }
}
