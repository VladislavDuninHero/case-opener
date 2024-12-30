package com.case_opener_game.case_opener.service.user.impl;

import com.case_opener_game.case_opener.dto.user.UserDTO;
import com.case_opener_game.case_opener.dto.user.UserResponseDTO;
import com.case_opener_game.case_opener.dto.wallet.WalletDTO;
import com.case_opener_game.case_opener.entity.User;
import com.case_opener_game.case_opener.entity.Wallet;
import com.case_opener_game.case_opener.enums.Role;
import com.case_opener_game.case_opener.repository.UserRepository;
import com.case_opener_game.case_opener.service.user.UserService;
import com.case_opener_game.case_opener.service.utils.mapping.UserMapper;
import com.case_opener_game.case_opener.service.wallet.WalletService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final WalletService walletService;

    public UserServiceImpl(
            UserRepository userRepository,
            UserMapper userMapper,
            WalletService walletService
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.walletService = walletService;
    }

    @Override
    public UserResponseDTO createUser(UserDTO userDTO) {
        User userEntity = userMapper.toEntity(userDTO);
        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(1000));
        userEntity.setWallet(wallet);

        User user = userRepository.save(userEntity);

        return userMapper.toDto(user);
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(userMapper::toDto).toList();
    }


}
