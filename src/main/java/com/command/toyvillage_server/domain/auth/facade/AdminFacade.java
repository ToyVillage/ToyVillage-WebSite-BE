package com.command.toyvillage_server.domain.auth.facade;

import com.command.toyvillage_server.domain.auth.domain.Admin;
import com.command.toyvillage_server.domain.auth.domain.repository.AdminRepository;
import com.command.toyvillage_server.domain.auth.exception.AdminNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminFacade {
    private final AdminRepository adminRepository;

    public Admin currentAdmin() {
        String adminName = SecurityContextHolder.getContext().getAuthentication().getName();

        return adminRepository.findByUsername(adminName)
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }
}
