package com.command.toyvillage_server.domain.common.auth.user.service;

import com.command.toyvillage_server.domain.common.auth.user.domain.User;
import com.command.toyvillage_server.domain.common.auth.user.domain.repository.UserRepository;
import com.command.toyvillage_server.domain.common.auth.user.presentation.dto.request.UserLoginRequest;
import com.command.toyvillage_server.domain.common.auth.common.exception.LoginInfoNotMatchedException;
import com.command.toyvillage_server.domain.common.auth.common.presentation.dto.response.TokenResponse;
import com.command.toyvillage_server.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse execute(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> LoginInfoNotMatchedException.EXCEPTION);

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw LoginInfoNotMatchedException.EXCEPTION;
        }

        return jwtTokenProvider.receiveUserToken(request.email());
    }
}
