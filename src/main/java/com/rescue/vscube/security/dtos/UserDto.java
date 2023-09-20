package com.rescue.vscube.security.dtos;

import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class UserDto {

    private Long id;
    private String name;
    private String login;
    private String token;
    private String description;
    private String registeredLocation;
    private Timestamp lastUpdated;
    private String phone;

}
