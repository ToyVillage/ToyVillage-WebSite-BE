package com.command.toyvillage_server.domain.app.auth.account.service;

import com.command.toyvillage_server.global.security.jwt.AppJwtTokenProvider;
import com.command.toyvillage_server.global.security.jwt.TokenPair;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppReissueService {
    private final AppJwtTokenProvider appJwtTokenProvider;

    public TokenPair execute(String refreshToken) {
        return appJwtTokenProvider.reissue(refreshToken);
    }
}
