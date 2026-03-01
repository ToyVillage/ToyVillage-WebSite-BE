package com.command.toyvillage_server.domain.auth.service;

import com.command.toyvillage_server.domain.auth.domain.Admin;
import com.command.toyvillage_server.domain.auth.domain.repository.AdminRepository;
import com.command.toyvillage_server.domain.auth.presentation.dto.request.AdminSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminSignUpService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(AdminSignUpRequest request) {
        String password = passwordEncoder.encode(request.password());

        if(adminRepository.findByUsername(request.username()).isPresent()){
            throw Admin
        }

        Admin admin = Admin.create(
                request.username(),
                password
        );

        adminRepository.save(admin);
    }
}
