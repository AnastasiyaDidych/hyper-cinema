package com.softserve.edu.hypercinema.util;

import com.softserve.edu.hypercinema.entity.UserEntity;
import org.springframework.security.core.Authentication;

public class AuthUtil {
    public static boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("ROLE_ADMIN"));
    }

    public static boolean isAdmin(UserEntity userEntity) {
        return userEntity.getRoles().stream().anyMatch(r -> r.getName().equals("ROLE_ADMIN"));
    }

    public static boolean isManager(Authentication authentication) {
        return authentication.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("ROLE_MANAGER"));
    }

    public static boolean isManager(UserEntity userEntity) {
        return userEntity.getRoles().stream().anyMatch(r -> r.getName().equals("ROLE_MANAGER"));
    }

    public static boolean isUser(Authentication authentication) {
        return authentication.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("ROLE_USER"));
    }

    public static boolean isUser(UserEntity userEntity) {
        return userEntity.getRoles().stream().anyMatch(r -> r.getName().equals("ROLE_USER"));
    }
}
