package com.command.toyvillage_server.domain.app.auth.admin.presentation.dto.response;

import com.command.toyvillage_server.domain.app.auth.admin.domain.AppAdmin;
import lombok.Builder;

@Builder
public record EmployeeResponse(
        Long id,
        String username,
        String name
) {
    public static EmployeeResponse from(AppAdmin employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .username(employee.getUsername())
                .name(employee.getName())
                .build();
    }
}
