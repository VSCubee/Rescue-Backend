package com.rescue.vscube.security.mappers;


import com.rescue.vscube.agency.Agency;
import com.rescue.vscube.security.dtos.SignUpDto;
import com.rescue.vscube.security.dtos.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(Agency agency);

    @Mapping(target = "password", ignore = true)
    Agency signUpToUser(SignUpDto signUpDto);

}
