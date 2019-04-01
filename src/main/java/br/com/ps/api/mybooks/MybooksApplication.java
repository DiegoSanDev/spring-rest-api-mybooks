package br.com.ps.api.mybooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MybooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybooksApplication.class, args);
		PasswordEncoder en = new BCryptPasswordEncoder();
		System.out.println(en.encode("123"));
	}

}
