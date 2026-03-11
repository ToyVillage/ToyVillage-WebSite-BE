package com.command.toyvillage_server.domain.auth.service;

import com.command.toyvillage_server.domain.auth.domain.Admin;
import com.command.toyvillage_server.domain.auth.domain.PasswordResetToken;
import com.command.toyvillage_server.domain.auth.domain.repository.AdminRepository;
import com.command.toyvillage_server.domain.auth.domain.repository.PasswordResetTokenRepository;
import com.command.toyvillage_server.domain.auth.exception.AdminNotFoundException;
import com.command.toyvillage_server.domain.auth.exception.ResetTokenNotFoundException;
import com.command.toyvillage_server.domain.auth.presentation.dto.request.ChangePasswordRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AdminChangePasswordService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Transactional
    public void execute(
            @RequestBody
            @Valid
            ChangePasswordRequest request){
        PasswordResetToken resetToken = passwordResetTokenRepository.findById(request.resetToken())
                .orElseThrow(() -> ResetTokenNotFoundException.EXCEPTION);

        Admin admin = adminRepository.findByEmail(resetToken.getEmail())
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);

        admin.changePassword(passwordEncoder.encode(request.newPassword()));
        adminRepository.save(admin);

        passwordResetTokenRepository.delete(resetToken);
    }
}