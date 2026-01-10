package com.floss.odontologia.security.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Hashed {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hashPassword(String password){
        return encoder.encode(password);
    }

    public static boolean matches(String password, String hashedPassword){
        return encoder.matches(password, hashedPassword);
    }
}
