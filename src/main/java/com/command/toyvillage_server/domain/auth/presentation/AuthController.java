package com.command.toyvillage_server.domain.auth.presentation;

import com.command.toyvillage_server.domain.auth.service.AdminLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AdminLoginService adminLoginService;

    @PostMapping("/login")
    public


}
