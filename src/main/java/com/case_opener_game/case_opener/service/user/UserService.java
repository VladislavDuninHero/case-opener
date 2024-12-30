package com.case_opener_game.case_opener.service.user;

import com.case_opener_game.case_opener.dto.user.UserDTO;
import com.case_opener_game.case_opener.dto.user.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserDTO userDTO);
    List<UserResponseDTO> getUsers();

}
