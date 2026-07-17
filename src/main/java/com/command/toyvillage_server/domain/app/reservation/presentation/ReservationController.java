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

    @PostMapping("/permission/{id}")
    public void setReservationPermission(@PathVariable Long id, @RequestBody boolean reservationPermission) {
        reservationPermissionSettingService.execute(id, reservationPermission);
    }

}