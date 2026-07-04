package com.command.toyvillage_server.domain.web.auth.service;

import com.command.toyvillage_server.domain.web.auth.domain.Admin;
import com.command.toyvillage_server.domain.web.auth.domain.RefreshToken;
import com.command.toyvillage_server.domain.web.auth.domain.repository.RefreshTokenRepository;
import com.command.toyvillage_server.domain.web.auth.exception.RefreshTokenNotFoundException;
import com.command.toyvillage_server.domain.web.auth.facade.AdminFacade;
import com.command.toyvillage_server.domain.web.auth.presentation.dto.response.TokenResponse;
import com.command.toyvillage_server.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminReissueService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AdminFacade adminFacade;

    public TokenResponse execute() {
        Admin admin = adminFacade.currentAdmin();

        RefreshToken refreshToken = refreshTokenRepository.findByUsername(admin.getEmail())
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        refreshTokenRepository.delete(refreshToken);

        return jwtTokenProvider.receiveToken(admin.getEmail());
    }
}
