package com.rescue.vscube.security.controllers;


import com.rescue.vscube.agency.AgencyService;
import com.rescue.vscube.security.config.UserAuthenticationProvider;
import com.rescue.vscube.security.dtos.CredentialsDto;
import com.rescue.vscube.security.dtos.SignUpDto;
import com.rescue.vscube.security.dtos.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("")
public class AuthController {

    private final AgencyService agencyService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/api/auth/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        UserDto userDto = agencyService.login(credentialsDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpDto user) {
        UserDto createdUser = agencyService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(createdUser));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

}
