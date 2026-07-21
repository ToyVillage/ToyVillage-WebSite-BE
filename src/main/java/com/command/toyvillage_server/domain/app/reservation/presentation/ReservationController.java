package com.command.toyvillage_server.domain.app.reservation.presentation;

import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationListResponse;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationResponse;
import com.command.toyvillage_server.domain.app.reservation.service.ReservationPermissionSettingService;
import com.command.toyvillage_server.domain.app.reservation.service.ReservationQueryListService;
import com.command.toyvillage_server.domain.app.reservation.service.ReservationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationQueryService reservationQueryService;
    private final ReservationQueryListService reservationQueryListService;
    private final ReservationPermissionSettingService reservationPermissionSettingService;

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

}