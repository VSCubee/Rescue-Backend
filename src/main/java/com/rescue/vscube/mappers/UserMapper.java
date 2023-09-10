package com.rescue.vscube.mappers;

import com.rescue.vscube.dtos.SignUpDto;
import com.rescue.vscube.dtos.UserDto;
import com.rescue.vscube.entites.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}
