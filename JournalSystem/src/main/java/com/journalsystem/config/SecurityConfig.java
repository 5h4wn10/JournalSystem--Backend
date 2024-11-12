package com.journalsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for Postman testing
                .authorizeRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()  // Allow unrestricted access to auth endpoints
                        .requestMatchers("/api/observations/**", "/api/conditions/**").authenticated()  // Require authentication for observation and condition endpoints
                        .anyRequest().permitAll()  // Permit all other requests (can adjust if needed)
                )
                .httpBasic(Customizer.withDefaults());  // Enable Basic Authentication

        return http.build();
    }
}
