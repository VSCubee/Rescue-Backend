package com.rescue.vscube.agency;


import com.rescue.vscube.security.dtos.CredentialsDto;
import com.rescue.vscube.security.dtos.SignUpDto;
import com.rescue.vscube.security.dtos.UserDto;
import com.rescue.vscube.security.exceptions.AppException;
import com.rescue.vscube.security.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AgencyService {

    private final AgencyRepository agencyRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto) {
        Agency agency = agencyRepository.findByLogin(credentialsDto.login())
                .orElseThrow(() -> new AppException("Unknown agency", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), agency.getPassword())) {
            return userMapper.toUserDto(agency);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<Agency> optionalUser = agencyRepository.findByLogin(userDto.login());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        Agency agency = userMapper.signUpToUser(userDto);
        agency.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));

        Agency savedAgency = agencyRepository.save(agency);

        return userMapper.toUserDto(savedAgency);
    }

    public UserDto findByLogin(String login) {
        Agency agency = agencyRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown agency", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(agency);
    }

}
