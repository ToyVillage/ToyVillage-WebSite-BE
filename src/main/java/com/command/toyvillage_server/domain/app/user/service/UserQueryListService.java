package com.command.toyvillage_server.domain.app.user.service;

import com.command.toyvillage_server.domain.common.auth.user.domain.repository.UserRepository;
import com.command.toyvillage_server.domain.app.user.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueryListService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserResponse> execute() {
        return userRepository.findAll()
            .stream()
            .map(UserResponse::of)
            .toList();
    }
}
