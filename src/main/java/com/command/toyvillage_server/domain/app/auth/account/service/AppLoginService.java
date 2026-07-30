package com.command.toyvillage_server.domain.app.auth.account.service;

import com.command.toyvillage_server.domain.app.auth.account.domain.AppAccount;
import com.command.toyvillage_server.domain.app.auth.account.domain.repository.AppAccountRepository;
import com.command.toyvillage_server.domain.app.auth.account.presentation.dto.request.AppLoginRequest;
import com.command.toyvillage_server.domain.app.auth.account.presentation.dto.response.AppLoginResponse;
import com.command.toyvillage_server.global.security.exception.LoginInfoNotMatchedException;
import com.command.toyvillage_server.global.security.jwt.AppJwtTokenProvider;
import com.command.toyvillage_server.global.security.jwt.TokenPair;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppLoginService {
    private final AppAccountRepository appAccountRepository;
    private final AppJwtTokenProvider appJwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public AppLoginResponse execute(AppLoginRequest request) {
        AppAccount account = appAccountRepository.findByUsername(request.username())
                .orElseThrow(() -> LoginInfoNotMatchedException.EXCEPTION);

        if (!passwordEncoder.matches(request.password(), account.getPassword())) {
            throw LoginInfoNotMatchedException.EXCEPTION;
        }

        TokenPair tokens = appJwtTokenProvider.issue(account);
        return AppLoginResponse.of(tokens, account);
    }
}
