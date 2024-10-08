package com.novosoft.assessment.config;


import com.novosoft.assessment.repositorys.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserRepo userRepo;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/novo/users/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/novo/create/product").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/novo/update/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/novo/delete/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/novo/products/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/novo/get/products").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
