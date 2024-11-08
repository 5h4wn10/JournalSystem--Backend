package com.journalsystem.config;

import com.journalsystem.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

   /* @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/api/auth/register")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/auth/login")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/public/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/patient/me")).hasRole("PATIENT")
                        .requestMatchers(new AntPathRequestMatcher("/api/patient/**")).hasAnyRole("DOCTOR", "STAFF")
                        .requestMatchers(new AntPathRequestMatcher("/api/doctor/**")).hasRole("DOCTOR")
                        .requestMatchers(new AntPathRequestMatcher("/api/staff/**")).hasRole("STAFF")
                        .anyRequest().authenticated()
                );

        return http.build();
    }*/

   /* ALLMÄN TESTANDE INGEN AUTENTISERING@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Stäng av CSRF för enklare testning
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Tillåt alla förfrågningar utan autentisering
                );

        return http.build();
    }*/

    /* SKAPAR INLOGGNINGSFORMULÄR; ANVÄNDES FÖR INLOG TEST. @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Avaktivera CSRF för enkel testning
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/public/**").permitAll() // Tillåt alla att nå login, register och public endpoints
                        .anyRequest().authenticated() // Kräver autentisering för övriga endpoints
                )
                .formLogin(form -> form
                        .permitAll()          // Tillåt alla att nå inloggningssidan
                )
                .logout(logout -> logout.permitAll()); // Tillåt alla att logga ut

        return http.build();
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Stäng av CSRF för enkel testning
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/observations/**", "/api/conditions/**").authenticated() // Kräver autentisering
                        .anyRequest().permitAll() // Tillåt övriga förfrågningar utan autentisering
                )
                .httpBasic(Customizer.withDefaults()); // Ny syntax för Basic Authentication

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Exempel på en practitioner-användare för testning
        UserDetails practitioner = User.withDefaultPasswordEncoder()
                .username("practitionerUser")
                .password("password123")
                .roles("DOCTOR")
                .roles("STAFF")// Sätt rollen PRACTITIONER
                .build();

        return new InMemoryUserDetailsManager(practitioner);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
