package com.softserve.edu.hypercinema.constants;

public class SecurityConstants {
    public static final String SECRET = "SecretKey";
    public static final long EXPIRATION_TIME = 86400; // 1 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/users/sign-up";
}
