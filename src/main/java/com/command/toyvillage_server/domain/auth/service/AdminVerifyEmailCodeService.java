package com.command.toyvillage_server.domain.auth.service;

import com.command.toyvillage_server.domain.auth.domain.EmailVerification;
import com.command.toyvillage_server.domain.auth.domain.PasswordResetToken;
import com.command.toyvillage_server.domain.auth.domain.repository.EmailVerificationRepository;
import com.command.toyvillage_server.domain.auth.domain.repository.PasswordResetTokenRepository;
import com.command.toyvillage_server.domain.auth.exception.EmailNotMatchedException;
import com.command.toyvillage_server.domain.auth.exception.VerificationCodeExpiredException;
import com.command.toyvillage_server.domain.auth.exception.VerificationCodeNotMatchedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminVerifyEmailCodeService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final EmailVerificationRepository emailVerificationRepository;

    public String execute(String email, String code){
        EmailVerification verification = emailVerificationRepository.findById(email)
                .orElseThrow(() -> VerificationCodeExpiredException.EXCEPTION);

        if (!verification.isValid(code)){
            throw VerificationCodeNotMatchedException.EXCEPTION;
        }

        emailVerificationRepository.delete(verification);

        PasswordResetToken resetToken = PasswordResetToken.create(email);
        passwordResetTokenRepository.save(resetToken);

        return resetToken.getToken();
    }
}