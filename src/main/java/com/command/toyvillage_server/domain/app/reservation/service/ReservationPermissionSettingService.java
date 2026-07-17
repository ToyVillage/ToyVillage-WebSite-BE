package com.command.toyvillage_server.domain.app.reservation.service;

import com.command.toyvillage_server.domain.app.user.domain.User;
import com.command.toyvillage_server.domain.app.user.domain.repository.UserRepository;
import com.command.toyvillage_server.domain.app.user.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationPermissionSettingService {
    private final UserRepository userRepository;

    @Transactional
    public void execute(Long userId, boolean permission) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        user.setReservationPermission(permission);
    }
}
