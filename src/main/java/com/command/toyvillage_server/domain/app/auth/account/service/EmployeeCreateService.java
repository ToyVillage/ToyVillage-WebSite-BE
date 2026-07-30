package com.command.toyvillage_server.domain.app.auth.account.service;

import com.command.toyvillage_server.domain.app.auth.account.domain.AppAccount;
import com.command.toyvillage_server.domain.app.auth.account.domain.repository.AppAccountRepository;
import com.command.toyvillage_server.domain.app.auth.account.exception.AppAccountAlreadyExistsException;
import com.command.toyvillage_server.domain.app.auth.account.presentation.dto.request.EmployeeCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeCreateService {
    private final AppAccountRepository appAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(EmployeeCreateRequest request) {
        if (appAccountRepository.existsByUsername(request.username())) {
            throw AppAccountAlreadyExistsException.EXCEPTION;
        }

        AppAccount employee = AppAccount.createEmployee(
                request.username(),
                request.name(),
                passwordEncoder.encode(request.username())
        );
        appAccountRepository.save(employee);
    }
}
