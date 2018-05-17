package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.UserEntity;
import org.springframework.security.core.Authentication;

import java.security.Principal;
import java.util.List;

public interface UserService {

    UserEntity getUser(Long id);

    UserEntity getUser(Principal principal);

   UserEntity getUser(Authentication authentication);

    void createUser(UserEntity userEntity);

    void updateUser(UserEntity userEntity, Principal principal);

    void deleteUser(Long id);

}
