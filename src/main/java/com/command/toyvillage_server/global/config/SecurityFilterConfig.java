package com.command.toyvillage_server.global.config;

import com.command.toyvillage_server.global.error.GlobalExceptionFilter;
import com.command.toyvillage_server.global.security.jwt.AppJwtTokenProvider;
import com.command.toyvillage_server.global.security.jwt.JwtAuthenticationFilter;
import com.command.toyvillage_server.global.security.jwt.WebJwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class SecurityFilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final WebJwtTokenProvider webJwtTokenProvider;
    private final AppJwtTokenProvider appJwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Override
    public void configure(HttpSecurity http) {

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(
                webJwtTokenProvider,
                appJwtTokenProvider
        );
        GlobalExceptionFilter globalExceptionFilter = new GlobalExceptionFilter(objectMapper);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(globalExceptionFilter, JwtAuthenticationFilter.class);
    }
}
