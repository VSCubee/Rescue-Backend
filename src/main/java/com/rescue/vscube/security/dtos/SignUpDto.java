package com.rescue.vscube.security.dtos;

public record SignUpDto (String name, String login, char[] password, String description, String registeredLocation, String phone) { }
