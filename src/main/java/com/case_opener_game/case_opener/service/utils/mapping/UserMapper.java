package com.case_opener_game.case_opener.service.utils.mapping;

import com.case_opener_game.case_opener.dto.user.UserDTO;
import com.case_opener_game.case_opener.dto.user.UserResponseDTO;
import com.case_opener_game.case_opener.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "")
    User toEntity(UserDTO userDTO);

    @Mapping(target = "")
    UserResponseDTO toDto(User user);
}
