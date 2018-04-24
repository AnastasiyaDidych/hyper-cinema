package com.softserve.edu.hypercinema.filter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.edu.hypercinema.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

import static com.softserve.edu.hypercinema.constants.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String CREDENTIALS_READING_ERROR_MESSAGE = "Can not read credentials";

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.setFilterProcessesUrl("/api/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            UserDto userDto = new ObjectMapper().readValue(request.getInputStream(), UserDto.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userDto.getEmail(),
                    userDto.getPassword(),
                    Collections.emptyList()));
        } catch (IOException e) {
            //TODO prepare special Exception
            e.printStackTrace();
            throw new RuntimeException(CREDENTIALS_READING_ERROR_MESSAGE);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // get username from Authentication
        // in our case it is email
        String email = ((User) authResult.getPrincipal()).getUsername();
        //get list of ROLES
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", authResult.getAuthorities()
                .stream().map(Object::toString).collect(Collectors.toList()));

        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();

//        System.out.println("Token = " + token);
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
