package com.kob.backedn2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Backedn2Application {
    public static void main(String[] args) {
        SpringApplication.run(Backedn2Application.class, args);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("111"));
        System.out.println(passwordEncoder.encode("211"));
        System.out.println(passwordEncoder.encode("21"));
        System.out.println(passwordEncoder.encode("121"));


    }

}
