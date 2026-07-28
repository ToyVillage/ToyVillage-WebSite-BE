package com.command.toyvillage_server.domain.app.reservation.presentation;

import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationListResponse;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationPermissionResponse;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationResponse;
import com.command.toyvillage_server.domain.app.reservation.service.ReservationPermissionDeleteService;
import com.command.toyvillage_server.domain.app.reservation.service.ReservationPermissionQueryListService;
import com.command.toyvillage_server.domain.app.reservation.service.ReservationPermissionSettingService;
import com.command.toyvillage_server.domain.app.reservation.service.ReservationQueryListService;
import com.command.toyvillage_server.domain.app.reservation.service.ReservationQueryService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationQueryService reservationQueryService;
    private final ReservationQueryListService reservationQueryListService;
    private final ReservationPermissionSettingService reservationPermissionSettingService;
    private final ReservationPermissionQueryListService reservationPermissionQueryListService;
    private final ReservationPermissionDeleteService reservationPermissionDeleteService;

    @GetMapping("/{id}")
    public ReservationResponse getDetail(@PathVariable Long id) {
        return reservationQueryService.execute(id);
    }

    @GetMapping()
    public List<ReservationListResponse> getList() {
        return reservationQueryListService.execute();
    }

    @PostMapping("/permission/{reservationId}/{userId}")
    public void setReservationPermission(
        @PathVariable("reservationId") Long reservationId,
        @PathVariable("userId") Long userId,
        @RequestBody boolean reservationPermission
    ) {
        reservationPermissionSettingService.execute(reservationId, userId, reservationPermission);
    }

    @GetMapping("/permission/{reservationId}")
    public List<ReservationPermissionResponse> getPermissionList(@PathVariable("reservationId") Long reservationId) {
        return reservationPermissionQueryListService.execute(reservationId);
    }

    @DeleteMapping("/permission/{reservationId}/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponse deletePermission(
        @PathVariable Long reservationId,
        @PathVariable Long userId
    ) {
        reservationPermissionDeleteService.execute(reservationId, userId);
        return MessageResponse.of("단체예약 삭제가 완료되었습니다.");
    }
}
