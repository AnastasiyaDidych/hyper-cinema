package com.softserve.edu.hypercinema.controller;

import com.softserve.edu.hypercinema.converter.UserConverter;
import com.softserve.edu.hypercinema.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.*;
import com.softserve.edu.hypercinema.dto.UserDto;
import com.softserve.edu.hypercinema.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserConverter userConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto userDto) {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
       try {
           userService.createUser(userConverter.convertToEntity(userDto));
       } catch(UserAlreadyExistsException e) {
           e.printStackTrace();
       }
    }


    @PreAuthorize("hasRole('USER')")
    @PutMapping
    public void updateUser(@RequestBody UserDto userDto, Principal principal) {
        userService.updateUser(userConverter.convertToEntity(userDto), principal);
    }


    @PreAuthorize("hasRole('USER')")
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
