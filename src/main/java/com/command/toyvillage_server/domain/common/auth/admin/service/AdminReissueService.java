package com.command.toyvillage_server.domain.common.auth.admin.service;

import com.command.toyvillage_server.domain.common.auth.admin.domain.Admin;
import com.command.toyvillage_server.domain.common.auth.common.domain.RefreshToken;
import com.command.toyvillage_server.domain.common.auth.common.domain.repository.RefreshTokenRepository;
import com.command.toyvillage_server.domain.common.auth.common.exception.RefreshTokenNotFoundException;
import com.command.toyvillage_server.domain.common.auth.admin.facade.AdminFacade;
import com.command.toyvillage_server.domain.common.auth.common.presentation.dto.response.TokenResponse;
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
