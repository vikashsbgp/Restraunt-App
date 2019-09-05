package com.vikash.restraunt.security;

public class JwtProperties {

    public static final String SECRET = "VikashKnowsSpringBoot";
    public static final int EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
}
