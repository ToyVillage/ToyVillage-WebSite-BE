package com.command.toyvillage_server.domain.app.user.service;

import com.command.toyvillage_server.domain.app.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueryListService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> execute() {
        return userRepository.findAll();
    }
}
