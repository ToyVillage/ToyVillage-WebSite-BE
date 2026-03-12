package com.command.toyvillage_server.domain.auth.presentation;

import com.command.toyvillage_server.domain.auth.presentation.dto.request.*;
import com.command.toyvillage_server.domain.auth.presentation.dto.response.AdminVerifyEmailCodeResponse;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import com.command.toyvillage_server.domain.auth.presentation.dto.response.AccessTokenResponse;
import com.command.toyvillage_server.domain.auth.presentation.dto.response.TokenResponse;
import com.command.toyvillage_server.domain.auth.service.*;
import com.command.toyvillage_server.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AdminLoginService adminLoginService;
    private final AdminSignUpService adminSignUpService;
    private final AdminReissueService adminReissueService;
    private final AdminPasswordResetService adminPasswordResetService;
    private final AdminChangePasswordService adminChangePasswordService;
    private final AdminVerifyEmailCodeService adminVerifyEmailCodeService;

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

    @PatchMapping("/reissue")
    public ResponseEntity<AccessTokenResponse> reissue(
            HttpServletResponse response
    ){
        TokenResponse result = adminReissueService.execute();

        cookieUtil.addRefreshTokenCookie(response, result.refreshToken());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(AccessTokenResponse.of(result.accessToken()));
    }

    @PostMapping("password/verification")
    public ResponseEntity<MessageResponse> sendVerificationEmail(
        @RequestBody
        @Valid
        AdminPasswordResetRequest request
    ){
        adminPasswordResetService.execute(request.email());

        return ResponseEntity.ok(
                MessageResponse.of("해당 이메일로 인증번호가 발송되었습니다, 인증코드를 입력해주세요.")
        );
    }

    @PostMapping("password/verification/confirm")
    public ResponseEntity<AdminVerifyEmailCodeResponse> verifyEmailCode(
            @RequestBody @Valid AdminVerifyEmailCodeRequest request
    ){
        String resetToken = adminVerifyEmailCodeService.execute(request.email(), request.code());

        return ResponseEntity.ok(AdminVerifyEmailCodeResponse.of(resetToken));
    }

    @PatchMapping("password")
    public ResponseEntity<MessageResponse> changePassword(
            @RequestBody
            @Valid
            ChangePasswordRequest request
    ){
        adminChangePasswordService.execute(request);

        return ResponseEntity.ok(
                MessageResponse.of("비밀번호가 변경되었습니다.")
        );
    }
}
