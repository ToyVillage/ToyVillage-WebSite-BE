package com.command.toyvillage_server.domain.app.auth.account.service;

import com.command.toyvillage_server.domain.web.auth.admin.domain.RefreshToken;
import com.command.toyvillage_server.domain.web.auth.admin.domain.repository.RefreshTokenRepository;
import com.command.toyvillage_server.domain.web.auth.admin.exception.RefreshTokenNotFoundException;
import com.command.toyvillage_server.domain.web.auth.admin.presentation.dto.response.TokenResponse;
import com.command.toyvillage_server.global.security.jwt.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppReissueService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenResponse execute(String token) {
        Claims claims = jwtTokenProvider.getAppRefreshClaims(token);
        String tokenKey = jwtTokenProvider.getAppRefreshTokenKey(claims.getSubject());

        RefreshToken refreshToken = refreshTokenRepository.findByUsername(tokenKey)
                .filter(savedToken -> savedToken.getToken().equals(token))
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        refreshTokenRepository.delete(refreshToken);
        return jwtTokenProvider.receiveAppToken(claims.getSubject());
    }
}
