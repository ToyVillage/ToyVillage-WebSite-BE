package com.command.toyvillage_server.domain.auth.service;

import com.command.toyvillage_server.domain.auth.domain.Admin;
import com.command.toyvillage_server.domain.auth.domain.repository.AdminRepository;
import com.command.toyvillage_server.domain.auth.exception.LoginInfoNotMatchedException;
import com.command.toyvillage_server.domain.auth.presentation.dto.request.AdminLoginRequest;
import com.command.toyvillage_server.domain.auth.presentation.dto.response.TokenResponse;
import com.command.toyvillage_server.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminLoginService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse execute(AdminLoginRequest request){
        log.info("로그인 시도 / username : {}", request.email());

        Admin admin = adminRepository.findByEmail(request.email())
                .orElseThrow(() -> LoginInfoNotMatchedException.EXCEPTION);

        if(!passwordEncoder.matches(request.password(), admin.getPassword())) {
            throw LoginInfoNotMatchedException.EXCEPTION;
        }

        TokenResponse response = jwtTokenProvider.receiveToken(request.email());

        log.info("로그인 성공 / username : {}", request.email());

        return response;
    }
}
