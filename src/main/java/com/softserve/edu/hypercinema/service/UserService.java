package com.softserve.edu.hypercinema.service;

import com.softserve.edu.hypercinema.entity.UserEntity;

import java.security.Principal;

public interface UserService {

    UserEntity getUser(Long id);

    UserEntity getUser(Principal principal);

//    UserEntity getUser(Authentication authentication);

    void createUser(UserEntity userEntity);

    void updateUser(UserEntity userEntity);

    void updateUser(UserEntity userEntity, Principal principal);

    void deleteUser(Long id);

}
