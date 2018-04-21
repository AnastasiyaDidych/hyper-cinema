package com.softserve.edu.hypercinema.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.softserve.edu.hypercinema.entity.RoleEntity;
import com.softserve.edu.hypercinema.entity.UserEntity;
import com.softserve.edu.hypercinema.exception.UserAlreadyExistsException;
import com.softserve.edu.hypercinema.exception.UserNotFoundException;
import com.softserve.edu.hypercinema.repository.RoleRepository;
import com.softserve.edu.hypercinema.repository.UserRepository;
import com.softserve.edu.hypercinema.service.UserService;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String USER_ID_NOT_FOUND_MESSAGE = "Could not find user with id=";
    private static final String USER_EMAIL_NOT_FOUND_MESSAGE = "Could not find user with email=";
    private static final String USER_ALREADY_EXISTS_MESSAGE = "User with email=%s already exists";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserEntity getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(USER_ID_NOT_FOUND_MESSAGE + id));
    }

    @Override
    public UserEntity getUser(Principal principal) {
        return userRepository.findByEmail(principal.getName()).orElseThrow(() ->
                new UserNotFoundException(USER_EMAIL_NOT_FOUND_MESSAGE + principal.getName()));
    }

    /*
    @Override
    public UserEntity getUser(Authentication authentication) {
        String email =  ((String) authentication.getPrincipal());
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException(USER_EMAIL_NOT_FOUND_MESSAGE + email));
    }*/

    @Override
    public void createUser(UserEntity userEntity) {
        userRepository.findByEmail(userEntity.getEmail()).ifPresent(user -> {
            throw new UserAlreadyExistsException(String.format(USER_ALREADY_EXISTS_MESSAGE, user.getEmail()));
        });
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        ArrayList<RoleEntity> roleEntities = new ArrayList<>();
        roleEntities.add(roleRepository.findByName("ROLE_USER").get());
        userEntity.setRoles(roleEntities);
        userRepository.save(userEntity);
        log.info("New user account created: {}", userEntity.getEmail());
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public void updateUser(UserEntity userEntity, Principal principal) {
        UserEntity user = getUser(principal);
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setPhone(userEntity.getPhone());
        if (StringUtils.isNotEmpty(userEntity.getPassword())) {
            //user.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        }
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = getUser(id);
        userRepository.deleteById(id);
        log.info("User deleted: {}", user.getEmail());
    }

}
