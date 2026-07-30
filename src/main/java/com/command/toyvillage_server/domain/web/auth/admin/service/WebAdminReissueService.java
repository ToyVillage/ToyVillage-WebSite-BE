package com.command.toyvillage_server.domain.web.auth.admin.service;

import com.command.toyvillage_server.global.security.jwt.TokenPair;
import com.command.toyvillage_server.global.security.jwt.WebJwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebAdminReissueService {
    private final WebJwtTokenProvider webJwtTokenProvider;

    public TokenPair execute(String refreshToken) {
        return webJwtTokenProvider.reissue(refreshToken);
    }
}
