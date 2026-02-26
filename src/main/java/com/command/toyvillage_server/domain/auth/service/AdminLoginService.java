package com.command.toyvillage_server.domain.auth.service;

import com.command.toyvillage_server.domain.animal.domain.repository.RefreshTokenRepository;
import com.command.toyvillage_server.domain.auth.domain.repository.AdminRepository;
import com.command.toyvillage_server.domain.auth.presentation.dto.request.AdminLoginRequest;
import com.command.toyvillage_server.domain.auth.presentation.dto.response.TokenResponse;
import com.command.toyvillage_server.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminLoginService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AdminRepository adminRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponse execute(AdminLoginRequest request){

    }
}
