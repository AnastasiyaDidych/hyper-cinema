package com.softserve.edu.hypercinema.dto;

import lombok.Data;

@Data
public class UserCredentialsDto extends BaseDto {

    private String email;
    private String password;

}
