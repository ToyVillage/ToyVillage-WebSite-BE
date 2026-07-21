package com.command.toyvillage_server.domain.common.auth.admin.facade;

import com.command.toyvillage_server.domain.common.auth.admin.domain.Admin;
import com.command.toyvillage_server.domain.common.auth.admin.domain.repository.AdminRepository;
import com.command.toyvillage_server.domain.common.auth.admin.exception.AdminNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminFacade {
    private final AdminRepository adminRepository;

    public Admin currentAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
                throw AdminNotFoundException.EXCEPTION;
        }

        String email = authentication.getName();

        return adminRepository.findByEmail(email)
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }
}
