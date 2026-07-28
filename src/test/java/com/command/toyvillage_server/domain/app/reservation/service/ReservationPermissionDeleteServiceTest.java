package com.command.toyvillage_server.domain.app.reservation.service;

import com.command.toyvillage_server.domain.app.reservation.domain.ReservationPermission;
import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationPermissionRepository;
import com.command.toyvillage_server.domain.app.reservation.exception.ReservationPermissionNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationPermissionDeleteServiceTest {

    @Mock
    private ReservationPermissionRepository reservationPermissionRepository;

    @Mock
    private ReservationPermission reservationPermission;

    private ReservationPermissionDeleteService reservationPermissionDeleteService;

    @BeforeEach
    void setUp() {
        reservationPermissionDeleteService =
            new ReservationPermissionDeleteService(reservationPermissionRepository);
    }

    @Test
    void 예약과_사용자의_조회_권한을_삭제한다() {
        Long reservationId = 1L;
        Long userId = 2L;
        when(reservationPermissionRepository.findByReservation_IdAndUser_Id(reservationId, userId))
            .thenReturn(Optional.of(reservationPermission));

        reservationPermissionDeleteService.execute(reservationId, userId);

        verify(reservationPermissionRepository).delete(reservationPermission);
    }

    @Test
    void 조회_권한이_없으면_예외가_발생한다() {
        Long reservationId = 1L;
        Long userId = 2L;
        when(reservationPermissionRepository.findByReservation_IdAndUser_Id(reservationId, userId))
            .thenReturn(Optional.empty());

        ReservationPermissionNotFoundException exception = assertThrows(
            ReservationPermissionNotFoundException.class,
            () -> reservationPermissionDeleteService.execute(reservationId, userId)
        );

        assertSame(ReservationPermissionNotFoundException.EXCEPTION, exception);
        verify(reservationPermissionRepository, never()).delete(reservationPermission);
    }
}
