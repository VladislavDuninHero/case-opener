package com.case_opener_game.case_opener.service.utils.mapping;

import com.case_opener_game.case_opener.dto.user.UserDTO;
import com.case_opener_game.case_opener.dto.user.UserResponseDTO;
import com.case_opener_game.case_opener.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "login", target = "login")
    @Mapping(source = "password", target = "password")
    User toEntity(UserDTO userDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "login", target = "login")
    UserResponseDTO toDto(User user);
}
