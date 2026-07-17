package com.command.toyvillage_server.domain.app.reservation.service;

import com.command.toyvillage_server.domain.app.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationPermissionSettingService {
    private final UserRepository userRepository;

    public void execute(Long userId, Long reservationId) {

    }
}
