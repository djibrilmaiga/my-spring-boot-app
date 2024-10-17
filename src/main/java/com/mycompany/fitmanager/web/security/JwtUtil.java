package com.mycompany.fitmanager.web.security;


public class JwtUtil {
    public static final String JWT_SECRET = "6ecf1c8dd8029be4c31421d56281e1de970833673ee88a256ec806738dbc6319";
    public static final Long JWT_EXPIRATION = 86400000L;
    public static final String AUTH_HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";
   // public static final String FORBIDDEN_MESSAGE = "Vous devez vous connecter pour accéder à cette page";
   // public static final String ACCESS_DENIED_MESSAGE = "Vous n'avez pas la permission d'accéder à ces ressources.";
}