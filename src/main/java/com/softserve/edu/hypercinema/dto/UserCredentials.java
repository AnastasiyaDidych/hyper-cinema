package com.softserve.edu.hypercinema.dto;

import lombok.Data;

@Data
public class UserCredentials extends BaseDto{
    private String email;
    private String password;
}
