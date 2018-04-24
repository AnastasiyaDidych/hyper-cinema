package com.softserve.edu.hypercinema.service.impl;

import com.softserve.edu.hypercinema.entity.RoleEntity;
import com.softserve.edu.hypercinema.entity.UserEntity;
import com.softserve.edu.hypercinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity roleEntity : userEntity.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(roleEntity.getName()));
        }
        return new User(userEntity.getEmail(),
                userEntity.getPassword(), authorities);
    }
}
