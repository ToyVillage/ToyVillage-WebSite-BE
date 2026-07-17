package com.command.toyvillage_server.domain.app.reservation.service;

import com.command.toyvillage_server.domain.app.user.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationPermissionSettingService {
    private final UserRepository userRepository;

    @Transactional
    public void execute(Long userId) {
        userRepository.findById(userId)
            .orElseThrow();
    }
}
