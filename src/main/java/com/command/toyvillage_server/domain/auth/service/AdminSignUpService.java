package com.command.toyvillage_server.domain.auth.service;

import com.command.toyvillage_server.domain.auth.domain.Admin;
import com.command.toyvillage_server.domain.auth.domain.repository.AdminRepository;
import com.command.toyvillage_server.domain.auth.presentation.dto.request.AdminSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminSignUpService {
    private final AdminRepository adminRepository;

    @Transactional
    public void execute(AdminSignUpRequest request) {
        Admin
    }
}
