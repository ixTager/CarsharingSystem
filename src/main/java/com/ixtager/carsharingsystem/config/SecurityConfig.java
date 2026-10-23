package com.ixtager.carsharingsystem.config;

import com.ixtager.carsharingsystem.jpa.UserRepository;
import com.ixtager.carsharingsystem.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;
import java.util.Set;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeHttpRequests  ->
                        authorizeHttpRequests
                                .requestMatchers(
                                        "/api/v1/cars", "/api/v1/cars/**",
                                        "/api/v1/logs", "/api/v1/logs/**",
                                        "/api/v1/tasks/", "/api/v1/tasks/**",
                                        "/api/v1/telemetry", "/api/v1/telemetry/**").hasRole("DISPATCHER")
                                .requestMatchers(
                                        "/api/v1/logs", "/api/v1/logs/**",
                                        "/api/v1/tasks/change_status", "/api/v1/cars/change_status/**")
                                .hasRole("MECHANIC")
                                .anyRequest().authenticated()
                )
                .build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository.findUserByEmail(username)
                    .orElse(null);
            Set<SimpleGrantedAuthority> roles = Collections.singleton(user.getRole().toAuthority());
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    roles
            );
        };
    }
}
