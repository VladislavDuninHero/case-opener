package com.case_opener_game.case_opener.service.utils.mapping;

import com.case_opener_game.case_opener.dto.user.UserRequestDTO;
import com.case_opener_game.case_opener.dto.user.UserResponseDTO;
import com.case_opener_game.case_opener.dto.wallet.WalletDTO;
import com.case_opener_game.case_opener.entity.User;
import com.case_opener_game.case_opener.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "login", target = "login")
    @Mapping(source = "password", target = "password")
    User toEntity(UserRequestDTO userRequestDTO);

    UserResponseDTO toDto(User user);
}
