package com.command.toyvillage_server.domain.auth.service;

import com.command.toyvillage_server.domain.auth.domain.Admin;
import com.command.toyvillage_server.domain.auth.domain.PasswordResetToken;
import com.command.toyvillage_server.domain.auth.domain.repository.AdminRepository;
import com.command.toyvillage_server.domain.auth.domain.repository.PasswordResetTokenRepository;
import com.command.toyvillage_server.domain.auth.exception.AdminNotFoundException;
import com.command.toyvillage_server.domain.auth.exception.ResetTokenNotFoundException;
import com.command.toyvillage_server.domain.auth.presentation.dto.request.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminChangePasswordService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Transactional
    public void execute(String email, Long id, ChangePasswordRequest request){

        PasswordResetToken resetToken = passwordResetTokenRepository.findById(request.resetToken())
                .orElseThrow(() -> ResetTokenNotFoundException.EXCEPTION);

        if (!resetToken.getEmail().equals(email)) {
            throw ResetTokenNotFoundException.EXCEPTION; // 또는 UnauthorizedException
        }

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);

        admin.changePassword(passwordEncoder.encode(request.newPassword()));
        adminRepository.save(admin);

        passwordResetTokenRepository.delete(resetToken);
    }
}