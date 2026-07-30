package com.command.toyvillage_server.domain.app.auth.account.presentation;

import com.command.toyvillage_server.domain.app.auth.account.presentation.dto.request.EmployeeCreateRequest;
import com.command.toyvillage_server.domain.app.auth.account.service.EmployeeCreateService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/admin")
@RequiredArgsConstructor
public class AppAdminController {
    private final EmployeeCreateService employeeCreateService;

    @PostMapping("/employees")
    public ResponseEntity<MessageResponse> createEmployee(
            @RequestBody @Valid EmployeeCreateRequest request
    ) {
        employeeCreateService.execute(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.of("직원 계정이 생성되었습니다."));
    }
}
