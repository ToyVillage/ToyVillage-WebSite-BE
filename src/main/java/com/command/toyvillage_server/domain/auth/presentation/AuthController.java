package com.command.toyvillage_server.domain.auth.presentation;

import com.command.toyvillage_server.domain.auth.presentation.dto.request.AdminLoginRequest;
import com.command.toyvillage_server.domain.auth.presentation.dto.response.TokenResponse;
import com.command.toyvillage_server.domain.auth.service.AdminLoginService;
import com.command.toyvillage_server.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AdminLoginService adminLoginService;

    private final CookieUtil cookieUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody AdminLoginRequest request,
            HttpServletResponse response
    ){
        TokenResponse result = adminLoginService.execute(request);

        cookieUtil.addRefreshTokenCookie(response, result.refreshToken());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result.accessToken());
    }
}
