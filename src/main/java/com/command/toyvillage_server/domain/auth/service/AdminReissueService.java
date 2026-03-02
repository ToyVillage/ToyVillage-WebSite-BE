package com.command.toyvillage_server.domain.auth.service;

import com.command.toyvillage_server.domain.auth.domain.repository.RefreshTokenRepository;
import com.command.toyvillage_server.domain.auth.exception.RefreshTokenNotFoundException;
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

    public TokenResponse execute(ReissueRequest request) {
        refreshTokenRepository.findByUsername(request.username())
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        return jwtTokenProvider.receiveToken(request.username());
    }
}
