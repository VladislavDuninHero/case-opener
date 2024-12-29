package com.case_opener_game.case_opener.service.user;

import com.case_opener_game.case_opener.dto.user.UserDTO;
import com.case_opener_game.case_opener.dto.user.UserResponseDTO;

public interface UserService {
    public UserResponseDTO createUser(UserDTO userDTO);
}
