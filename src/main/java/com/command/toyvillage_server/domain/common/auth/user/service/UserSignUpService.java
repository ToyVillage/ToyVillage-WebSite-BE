package com.command.toyvillage_server.domain.common.auth.user.service;

import com.command.toyvillage_server.domain.common.auth.common.domain.PasswordResetToken;
import com.command.toyvillage_server.domain.common.auth.common.domain.repository.PasswordResetTokenRepository;
import com.command.toyvillage_server.domain.common.auth.common.exception.ResetTokenNotFoundException;
import com.command.toyvillage_server.domain.common.auth.user.domain.User;
import com.command.toyvillage_server.domain.common.auth.user.domain.repository.UserRepository;
import com.command.toyvillage_server.domain.common.auth.user.exception.UserAlreadyException;
import com.command.toyvillage_server.domain.common.auth.user.presentation.dto.request.UserSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSignUpService {
    private final UserRepository userRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(UserSignUpRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw UserAlreadyException.EXCEPTION;
        }

        PasswordResetToken verifiedToken = passwordResetTokenRepository.findById(request.verificationToken())
                .filter(token -> token.getEmail().equals(request.email()))
                .orElseThrow(() -> ResetTokenNotFoundException.EXCEPTION);

        String password = passwordEncoder.encode(request.password());

        User user = User.create(request.name(), request.email(), password);
        userRepository.save(user);

        passwordResetTokenRepository.delete(verifiedToken);
    }
}
