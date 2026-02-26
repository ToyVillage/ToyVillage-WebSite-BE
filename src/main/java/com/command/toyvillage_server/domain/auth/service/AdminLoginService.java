package com.command.toyvillage_server.domain.auth.service;

import com.command.toyvillage_server.domain.animal.domain.repository.RefreshTokenRepository;
import com.command.toyvillage_server.domain.auth.domain.Admin;
import com.command.toyvillage_server.domain.auth.domain.repository.AdminRepository;
import com.command.toyvillage_server.domain.auth.exception.AdminNotFoundException;
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
        Admin admin = adminRepository.findByUsername(request.username())
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
        if(!passwordEncoder.matches(request.password(), admin.getPassword())) {
            throw .EXCEPTION;
        }

        String accessToken = jwtTokenProvider.createAccessToken(request.username());
        String refreshToken = jwtTokenProvider.createRefreshToken(request.username());

        return TokenResponse.of(accessToken, refreshToken);
    }
}
