package com.springboot.blog.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGeneratorEncoder {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("admin"));
        System.out.println(passwordEncoder.encode("sebastian"));
        System.out.println(passwordEncoder.matches("admin","$2a$10$x5n/TUB4VMr9Pc.0ZDgu1Op78poTzWgkV8nwDZBP/7xg5jmJsXVdm"));
    }
}
