package com.command.toyvillage_server.domain.auth.presentation;

import com.command.toyvillage_server.domain.animal.presentation.dto.response.MessageResponse;
import com.command.toyvillage_server.domain.auth.presentation.dto.request.AdminLoginRequest;
import com.command.toyvillage_server.domain.auth.presentation.dto.request.AdminSignUpRequest;
import com.command.toyvillage_server.domain.auth.presentation.dto.response.AccessTokenResponse;
import com.command.toyvillage_server.domain.auth.presentation.dto.response.TokenResponse;
import com.command.toyvillage_server.domain.auth.service.AdminLoginService;
import com.command.toyvillage_server.domain.auth.service.AdminSignUpService;
import com.command.toyvillage_server.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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
    private final AdminSignUpService adminSignUpService;

    private final CookieUtil cookieUtil;

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(
            @RequestBody @Valid AdminLoginRequest request,
            HttpServletResponse response
    ){
        TokenResponse result = adminLoginService.execute(request);

        cookieUtil.addRefreshTokenCookie(response, result.refreshToken());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(AccessTokenResponse.of(result.accessToken()));
    }
    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> signup(
            @RequestBody @Valid AdminSignUpRequest request
    ){
        adminSignUpService.execute(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.of("회원가입 완료되었습니다. 로그인 후 이용해주세요."));
    }
}
