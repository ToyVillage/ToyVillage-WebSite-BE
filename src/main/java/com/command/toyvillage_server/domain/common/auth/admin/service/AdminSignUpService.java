package com.command.toyvillage_server.domain.common.auth.admin.service;

import com.command.toyvillage_server.domain.common.auth.admin.domain.Admin;
import com.command.toyvillage_server.domain.common.auth.admin.domain.repository.AdminRepository;
import com.command.toyvillage_server.domain.common.auth.admin.exception.AdminAlreadyException;
import com.command.toyvillage_server.domain.common.auth.admin.presentation.dto.request.AdminSignUpRequest;
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
        if(adminRepository.findByEmail(request.email()).isPresent()){
            throw AdminAlreadyException.EXCEPTION;
        }

        String password = passwordEncoder.encode(request.password());

        Admin admin = Admin.create(
                request.email(),
                password
        );

        adminRepository.save(admin);
    }
}
