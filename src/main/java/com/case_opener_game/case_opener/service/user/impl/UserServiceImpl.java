package com.case_opener_game.case_opener.service.user.impl;

import com.case_opener_game.case_opener.dto.user.UserDTO;
import com.case_opener_game.case_opener.dto.user.UserRequestDTO;
import com.case_opener_game.case_opener.dto.user.UserResponseDTO;
import com.case_opener_game.case_opener.dto.wallet.WalletDTO;
import com.case_opener_game.case_opener.entity.User;
import com.case_opener_game.case_opener.entity.Wallet;
import com.case_opener_game.case_opener.repository.UserRepository;
import com.case_opener_game.case_opener.service.user.UserService;
import com.case_opener_game.case_opener.service.utils.mapping.UserMapper;
import com.case_opener_game.case_opener.service.wallet.WalletService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final WalletService walletService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            UserMapper userMapper,
            WalletService walletService,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.walletService = walletService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User userEntity = userMapper.toEntity(userRequestDTO);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

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

    @Override
    public UserDTO getUserByLogin(String login) {
        Optional<User> user = userRepository.getUserByLogin(login);
        User findedUser = user.orElseThrow();
        WalletDTO wallet = walletService.getWalletById(findedUser.getWallet().getId());

        UserDTO userDTO = new UserDTO(
                userMapper.toDto(findedUser),
                wallet
        );

        return userDTO;
    }

    @Override
    public UserResponseDTO login(UserRequestDTO userRequestDTO) {
        User user = userRepository.getUserByLogin(userRequestDTO.getLogin())
                .orElseThrow(() -> new UsernameNotFoundException(userRequestDTO.getLogin()));
        boolean matches = passwordEncoder.matches(userRequestDTO.getPassword(), user.getPassword());

        if (matches) {
            return userMapper.toDto(user);
        }

        throw new UsernameNotFoundException(userRequestDTO.getLogin());
    }


}
