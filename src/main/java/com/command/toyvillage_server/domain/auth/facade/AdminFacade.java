package com.command.toyvillage_server.domain.auth.facade;

import com.command.toyvillage_server.domain.auth.domain.Admin;
import com.command.toyvillage_server.domain.auth.domain.repository.AdminRepository;
import com.command.toyvillage_server.domain.auth.exception.AdminNotFoundException;
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

        String adminName = authentication.getName();

        return adminRepository.findByUsername(adminName)
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }
}
