package com.command.toyvillage_server.domain.app.reservation.service.admin;

import com.command.toyvillage_server.domain.app.auth.admin.domain.AppAdmin;
import com.command.toyvillage_server.domain.app.auth.admin.domain.AppAdminRole;
import com.command.toyvillage_server.domain.app.auth.admin.domain.repository.AppAdminRepository;
import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationPermissionRepository;
import com.command.toyvillage_server.domain.app.reservation.domain.repository.ReservationRepository;
import com.command.toyvillage_server.domain.app.reservation.exception.ReservationNotFoundException;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationEmployeeAssignResponse;
import com.command.toyvillage_server.domain.app.reservation.presentation.dto.response.ReservationPermissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationAdminEmployeeQueryListService {
    private final ReservationRepository reservationRepository;
    private final ReservationPermissionRepository reservationPermissionRepository;
    private final AppAdminRepository appAdminRepository;

    @Transactional(readOnly = true)
    public ReservationEmployeeAssignResponse execute(Long reservationId, String name) {
        if (!reservationRepository.existsById(reservationId)) {
            throw ReservationNotFoundException.EXCEPTION;
        }

        Set<Long> assignedIds = reservationPermissionRepository.findAllByReservation_Id(reservationId)
            .stream()
            .map(permission -> permission.getAppAdmin().getId())
            .collect(Collectors.toSet());

        Map<Boolean, List<ReservationPermissionResponse>> employees = appAdminRepository
            .findByRoleAndNameContainingOrderByNameAsc(AppAdminRole.EMPLOYEE, name == null ? "" : name.trim())
            .stream()
            .collect(Collectors.partitioningBy(
                employee -> assignedIds.contains(employee.getId()),
                Collectors.mapping(this::toResponse, Collectors.toList())
            ));

        return ReservationEmployeeAssignResponse.of(employees.get(true), employees.get(false));
    }

    private ReservationPermissionResponse toResponse(AppAdmin appAdmin) {
        return ReservationPermissionResponse.of(appAdmin.getId(), appAdmin.getName());
    }
}
