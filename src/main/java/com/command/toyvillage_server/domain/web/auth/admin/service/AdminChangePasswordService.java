package com.command.toyvillage_server.domain.web.auth.admin.service;

import com.command.toyvillage_server.domain.web.auth.admin.domain.Admin;
import com.command.toyvillage_server.domain.common.auth.common.domain.PasswordResetToken;
import com.command.toyvillage_server.domain.web.auth.admin.domain.repository.AdminRepository;
import com.command.toyvillage_server.domain.common.auth.common.domain.repository.PasswordResetTokenRepository;
import com.command.toyvillage_server.domain.web.auth.admin.exception.AdminNotFoundException;
import com.command.toyvillage_server.domain.common.auth.common.exception.ResetTokenNotFoundException;
import com.command.toyvillage_server.domain.web.auth.admin.presentation.dto.request.ChangePasswordRequest;
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
    public void execute(ChangePasswordRequest request){
        PasswordResetToken resetToken = passwordResetTokenRepository.findById(request.resetToken())
                .orElseThrow(() -> ResetTokenNotFoundException.EXCEPTION);

        Admin admin = adminRepository.findByEmail(resetToken.getEmail())
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);

        admin.changePassword(passwordEncoder.encode(request.newPassword()));
        adminRepository.save(admin);

        passwordResetTokenRepository.delete(resetToken);
    }
}