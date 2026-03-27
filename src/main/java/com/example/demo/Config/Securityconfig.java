package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Securityconfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/upload", "/search").permitAll() // public pages
                .anyRequest().authenticated() // others need login
            )
            .formLogin(form -> form
                .defaultSuccessUrl("/upload", true) // redirect to upload after login
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/") // redirect to home after logout
            );

        return http.build();
    }
}