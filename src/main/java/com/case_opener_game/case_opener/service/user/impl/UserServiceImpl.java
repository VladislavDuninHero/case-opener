package com.case_opener_game.case_opener.service.user.impl;

import com.case_opener_game.case_opener.dto.user.UserDTO;
import com.case_opener_game.case_opener.dto.user.UserResponseDTO;
import com.case_opener_game.case_opener.entity.User;
import com.case_opener_game.case_opener.repository.UserRepository;
import com.case_opener_game.case_opener.service.user.UserService;
import com.case_opener_game.case_opener.service.utils.mapping.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(
            UserRepository userRepository,
            UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDTO createUser(UserDTO userDTO) {

        User user = userRepository.save(userMapper.toEntity(userDTO));

        UserResponseDTO userResponseDTO = userMapper.toDto(user);

        return userResponseDTO;
    }
}
