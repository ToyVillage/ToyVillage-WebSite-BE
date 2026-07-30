package com.command.toyvillage_server.domain.app.auth.account.service;

import com.command.toyvillage_server.domain.app.auth.account.domain.AppAccount;
import com.command.toyvillage_server.domain.app.auth.account.domain.repository.AppAccountRepository;
import com.command.toyvillage_server.domain.app.auth.account.exception.AppAccountNotFoundException;
import com.command.toyvillage_server.domain.app.auth.account.presentation.dto.request.AppChangePasswordRequest;
import com.command.toyvillage_server.global.error.exception.ErrorCode;
import com.command.toyvillage_server.global.error.exception.ToyVillageException;
import com.command.toyvillage_server.global.security.auth.AppAccountDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppChangePasswordService {
    private final AppAccountRepository appAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(AppChangePasswordRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof AppAccountDetails details)) {
            throw AppAccountNotFoundException.EXCEPTION;
        }

        AppAccount account = appAccountRepository.findById(details.getId())
                .orElseThrow(() -> AppAccountNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.currentPassword(), account.getPassword())) {
            throw new ToyVillageException(ErrorCode.PASSWORD_MISMATCH);
        }

        account.changePassword(passwordEncoder.encode(request.newPassword()));
    }
}
