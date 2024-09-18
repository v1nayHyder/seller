package com.tech.vinay.seller.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF protection
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() // Allow all requests without authentication
                )
                .formLogin().disable() // Disable form-based login if used
                .httpBasic().disable(); // Disable HTTP Basic Authentication if used

        return http.build();
    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(
//                                "/api/v1/register",
//                                "/api/v1/master/stateList",
//                                "/api/v1/master/districtList",
//                                "/api/v1/master/tehsilList",
//                                "/api/v1/master/cityList"
//                        ).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .httpBasic();
//
//        return http.build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password encoding
    }
}
