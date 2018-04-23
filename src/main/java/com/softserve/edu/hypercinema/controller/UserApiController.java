package com.softserve.edu.hypercinema.controller;

import com.softserve.edu.hypercinema.entity.UserEntity;
import com.softserve.edu.hypercinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.softserve.edu.hypercinema.converter.UserConverter;
import com.softserve.edu.hypercinema.dto.UserDto;
import com.softserve.edu.hypercinema.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserApiController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody UserDto user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(userConverter.convertToEntity(user));
    }
//    @PostMapping
//    public void createUser(@RequestBody UserDto userDto) {
//        userService.createUser(userConverter.convertToEntity(userDto));
//    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public void updateUser(@RequestBody UserDto userDto, Principal principal) {
        userService.updateUser(userConverter.convertToEntity(userDto), principal);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userConverter.convertToDto(userService.getUser(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/me")
    public UserDto getCurrentUser(Principal principal) {
        return userConverter.convertToDto(userService.getUser(principal));
    }

}
