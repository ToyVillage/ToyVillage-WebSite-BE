package com.command.toyvillage_server.domain.app.reservation.presentation;

import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationAdminQueryListResponse;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationAdminQueryResponse;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationListResponse;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationPermissionResponse;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationResponse;
import com.command.toyvillage_server.domain.app.reservation.service.admin.ReservationAdminPermissionDeleteService;
import com.command.toyvillage_server.domain.app.reservation.service.admin.ReservationAdminPermissionQueryListService;
import com.command.toyvillage_server.domain.app.reservation.service.admin.ReservationAdminPermissionSettingService;
import com.command.toyvillage_server.domain.app.reservation.service.admin.ReservationAdminQueryListService;
import com.command.toyvillage_server.domain.app.reservation.service.admin.ReservationAdminQueryService;
import com.command.toyvillage_server.domain.app.reservation.service.employee.ReservationQueryListService;
import com.command.toyvillage_server.domain.app.reservation.service.employee.ReservationQueryService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationQueryService reservationQueryService;
    private final ReservationQueryListService reservationQueryListService;
    private final ReservationAdminPermissionSettingService reservationAdminPermissionSettingService;
    private final ReservationAdminPermissionQueryListService reservationAdminPermissionQueryListService;
    private final ReservationAdminPermissionDeleteService reservationAdminPermissionDeleteService;
    private final ReservationAdminQueryListService reservationAdminQueryListService;
    private final ReservationAdminQueryService reservationAdminQueryService;

    @GetMapping("/employee/{id}")
    public ReservationResponse getDetail(@PathVariable Long id) {
        return reservationQueryService.execute(id);
    }

    @GetMapping("/employee")
    public List<ReservationListResponse> getList() {
        return reservationQueryListService.execute();
    }

    @GetMapping
    public ReservationAdminQueryListResponse getReservationList(
        @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return reservationAdminQueryListService.execute(pageable);
    }

    @GetMapping("/{id}")
    public ReservationAdminQueryResponse getReservationDetail(@PathVariable Long id) {
        return reservationAdminQueryService.execute(id);
    }

    @PostMapping("/permission/{reservationId}/{appAdminId}")
    public void setReservationPermission(
        @PathVariable("reservationId") Long reservationId,
        @PathVariable("appAdminId") Long appAdminId,
        @RequestBody boolean reservationPermission
    ) {
        reservationAdminPermissionSettingService.execute(reservationId, appAdminId, reservationPermission);
    }

    @GetMapping("/permission/{reservationId}")
    public List<ReservationPermissionResponse> getPermissionList(@PathVariable("reservationId") Long reservationId) {
        return reservationAdminPermissionQueryListService.execute(reservationId);
    }

    @DeleteMapping("/permission/{reservationId}/{appAdminId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponse deletePermission(
        @PathVariable Long reservationId,
        @PathVariable Long appAdminId
    ) {
        reservationAdminPermissionDeleteService.execute(reservationId, appAdminId);
        return MessageResponse.of("단체예약 삭제가 완료되었습니다.");
    }
}
