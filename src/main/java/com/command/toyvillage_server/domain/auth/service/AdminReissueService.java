package com.command.toyvillage_server.domain.auth.service;

import com.command.toyvillage_server.domain.auth.domain.Admin;
import com.command.toyvillage_server.domain.auth.domain.RefreshToken;
import com.command.toyvillage_server.domain.auth.domain.repository.RefreshTokenRepository;
import com.command.toyvillage_server.domain.auth.exception.RefreshTokenNotFoundException;
import com.command.toyvillage_server.domain.auth.facade.AdminFacade;
import com.command.toyvillage_server.domain.auth.presentation.dto.request.ReissueRequest;
import com.command.toyvillage_server.domain.auth.presentation.dto.response.TokenResponse;
import com.command.toyvillage_server.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminReissueService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AdminFacade adminFacade;

    public TokenResponse execute(ReissueRequest request) {
        adminFacade.currentAdmin();

        RefreshToken refreshToken = refreshTokenRepository.findByUsername(request.username())
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        refreshTokenRepository.delete(refreshToken);

        return jwtTokenProvider.receiveToken(request.username());
    }
}
