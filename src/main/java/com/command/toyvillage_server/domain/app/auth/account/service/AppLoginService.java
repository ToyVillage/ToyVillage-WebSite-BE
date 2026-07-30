package com.command.toyvillage_server.domain.app.auth.account.service;

import com.command.toyvillage_server.domain.app.auth.account.domain.AppAccount;
import com.command.toyvillage_server.domain.app.auth.account.domain.repository.AppAccountRepository;
import com.command.toyvillage_server.domain.app.auth.account.presentation.dto.request.AppLoginRequest;
import com.command.toyvillage_server.domain.app.auth.account.presentation.dto.response.AppLoginResponse;
import com.command.toyvillage_server.domain.web.auth.admin.exception.LoginInfoNotMatchedException;
import com.command.toyvillage_server.domain.web.auth.admin.presentation.dto.response.TokenResponse;
import com.command.toyvillage_server.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppLoginService {
    private final AppAccountRepository appAccountRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public AppLoginResponse execute(AppLoginRequest request) {
        AppAccount account = appAccountRepository.findByUsername(request.username())
                .orElseThrow(() -> LoginInfoNotMatchedException.EXCEPTION);

        if (!passwordEncoder.matches(request.password(), account.getPassword())) {
            throw LoginInfoNotMatchedException.EXCEPTION;
        }

        TokenResponse tokens = jwtTokenProvider.receiveAppToken(account.getUsername());
        return AppLoginResponse.of(tokens, account);
    }
}
