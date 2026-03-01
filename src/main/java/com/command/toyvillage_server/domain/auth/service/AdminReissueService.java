package com.command.toyvillage_server.domain.auth.service;

import com.command.toyvillage_server.domain.auth.domain.repository.RefreshTokenRepository;
import com.command.toyvillage_server.domain.auth.presentation.dto.request.ReissueRequest;
import com.command.toyvillage_server.domain.auth.presentation.dto.response.AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminReissueService {
    private final RefreshTokenRepository refreshTokenRepository;

    public AccessTokenResponse execute(ReissueRequest request) {
        refreshTokenRepository.findByUsername(request.username())
                .orElseThrow(() -> )
    }
}
