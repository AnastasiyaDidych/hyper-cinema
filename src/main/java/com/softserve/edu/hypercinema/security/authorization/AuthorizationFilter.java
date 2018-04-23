package com.softserve.edu.hypercinema.security.authorization;

import com.softserve.edu.hypercinema.constants.SecurityConstants;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final UserDetailsService userDetailsService;

    public AuthorizationFilter(AuthenticationManager authManager,
                               UserDetailsService userDetailsService) {
        super(authManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String securityHeader = request.getHeader(SecurityConstants.HEADER_STRING);
        if (securityHeader == null || !securityHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
        } else {

            UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        }
    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String securityToken = request.getHeader(SecurityConstants.HEADER_STRING);
        if (securityToken != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET.getBytes())
                    //not good at line 50
                    .parseClaimsJws(securityToken.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                try {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(user);
                    UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(user, null, userDetails.getAuthorities());
                    return userToken;
                } catch (UsernameNotFoundException e) {
                    //logger.error(USERNAME_NOT_FOUND_MESSAGE + userName, e);
                }
            }
        }
        return null;
    }
}