package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.entity.UserEntity;
import com.softserve.edu.hypercinema.exception.UserNotFoundException;
import com.softserve.edu.hypercinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Collections.emptyList;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
       Optional<UserEntity> user = applicationUserRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(email);
        }
        return new User(user.get().getEmail(), user.get().getPassword(), emptyList());
    }
}
