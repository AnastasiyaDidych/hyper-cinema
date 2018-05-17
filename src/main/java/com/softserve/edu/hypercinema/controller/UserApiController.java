package com.softserve.edu.hypercinema.controller;

import com.softserve.edu.hypercinema.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;
import com.softserve.edu.hypercinema.dto.UserDto;
import com.softserve.edu.hypercinema.service.UserService;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/users")
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(userConverter.convertToEntity(userDto));
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
