package com.rescue.vscube.security.config;

import com.rescue.vscube.security.dtos.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtils {
    public static Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDto) {
                UserDto userDetails = (UserDto) principal;
                return userDetails.getId();
            }
        }
        return null;
    }
}




