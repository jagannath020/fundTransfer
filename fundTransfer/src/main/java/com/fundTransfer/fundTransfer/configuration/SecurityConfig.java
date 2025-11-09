package com.fundTransfer.fundTransfer.configuration;

import com.fundTransfer.fundTransfer.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean // CRITICAL: This configures the encoder used for password verification
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                // 1. Configure the custom user service
                .userDetailsService(customUserDetailsService)

                .authorizeHttpRequests(authorize -> authorize
                        // Permit registration and login
                        .requestMatchers("/api/register", "/api/loginPost", "/api/login").permitAll()
                        // Require authentication for all other endpoints
                        .anyRequest().authenticated()
                )
                // 2. Enable HTTP Basic Authentication
                .httpBasic(withDefaults());

        return http.build();
    }
}