package com.softserve.edu.hypercinema.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Data
public class UserDto extends BaseDto {

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

//    @JsonIgnoreProperties(ignoreUnknown = true)
    private String firstName;

//    @JsonIgnoreProperties(ignoreUnknown = true)
    private String lastName;

//    @JsonIgnoreProperties(ignoreUnknown = true)
    private String phone;

}
