package br.com.ps.api.mybooks.security.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityUtils {

    public static String criptografarSenha(String senha){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }
    
}