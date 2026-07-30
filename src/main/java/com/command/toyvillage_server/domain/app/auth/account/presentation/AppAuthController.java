package com.command.toyvillage_server.domain.app.auth.account.presentation;

import com.command.toyvillage_server.domain.app.auth.account.presentation.dto.request.AppChangePasswordRequest;
import com.command.toyvillage_server.domain.app.auth.account.presentation.dto.request.AppLoginRequest;
import com.command.toyvillage_server.domain.app.auth.account.presentation.dto.response.AppLoginResponse;
import com.command.toyvillage_server.domain.app.auth.account.service.AppChangePasswordService;
import com.command.toyvillage_server.domain.app.auth.account.service.AppLoginService;
import com.command.toyvillage_server.domain.app.auth.account.service.AppReissueService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import com.command.toyvillage_server.global.security.jwt.AuthTokenResponse;
import com.command.toyvillage_server.global.security.jwt.RefreshTokenRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/auth")
@RequiredArgsConstructor
public class AppAuthController {
    private final AppLoginService appLoginService;
    private final AppChangePasswordService appChangePasswordService;
    private final AppReissueService appReissueService;

    @PostMapping("/login")
    public ResponseEntity<AppLoginResponse> login(@RequestBody @Valid AppLoginRequest request) {
        return ResponseEntity.ok(appLoginService.execute(request));
    }

    @PatchMapping("/password")
    public ResponseEntity<MessageResponse> changePassword(
            @RequestBody @Valid AppChangePasswordRequest request
    ) {
        appChangePasswordService.execute(request);
        return ResponseEntity.ok(MessageResponse.of("비밀번호가 변경되었습니다."));
    }

    @PostMapping("/reissue")
    public ResponseEntity<AuthTokenResponse> reissue(@RequestBody @Valid RefreshTokenRequest request) {
        return ResponseEntity.ok(AuthTokenResponse.from(appReissueService.execute(request.refreshToken())));
    }
}
