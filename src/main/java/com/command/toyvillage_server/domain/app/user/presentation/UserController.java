package com.command.toyvillage_server.domain.app.user.presentation;

import com.command.toyvillage_server.domain.app.user.presentation.dto.response.UserResponse;
import com.command.toyvillage_server.domain.app.user.service.UserQueryListService;
import com.command.toyvillage_server.domain.web.auth.admin.presentation.dto.response.AccessTokenResponse;
import com.command.toyvillage_server.domain.web.auth.admin.presentation.dto.response.TokenResponse;
import com.command.toyvillage_server.domain.web.auth.admin.service.SendVerificationCodeService;
import com.command.toyvillage_server.domain.web.auth.admin.service.VerifyEmailCodeService;
import com.command.toyvillage_server.domain.app.user.presentation.dto.request.UserEmailVerificationRequest;
import com.command.toyvillage_server.domain.app.user.presentation.dto.request.UserLoginRequest;
import com.command.toyvillage_server.domain.app.user.presentation.dto.request.UserSignUpRequest;
import com.command.toyvillage_server.domain.app.user.presentation.dto.request.UserVerifyEmailCodeRequest;
import com.command.toyvillage_server.domain.app.user.presentation.dto.response.UserVerifyEmailCodeResponse;
import com.command.toyvillage_server.domain.app.user.service.UserLoginService;
import com.command.toyvillage_server.domain.app.user.service.UserSignUpService;
import com.command.toyvillage_server.global.common.response.MessageResponse;
import com.command.toyvillage_server.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserQueryListService userQueryListService;
    private final UserLoginService userLoginService;
    private final UserSignUpService userSignUpService;
    private final SendVerificationCodeService sendVerificationCodeService;
    private final VerifyEmailCodeService verifyEmailCodeService;
    private final CookieUtil cookieUtil;

    @GetMapping
    public List<UserResponse> getUserList(){
        return userQueryListService.execute();
    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(
            @RequestBody @Valid UserLoginRequest request,
            HttpServletResponse response
    ) {
        TokenResponse result = userLoginService.execute(request);

        cookieUtil.addRefreshTokenCookie(response, result.refreshToken());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(AccessTokenResponse.of(result.accessToken()));
    }

    @PostMapping("/signup/verification")
    public ResponseEntity<MessageResponse> sendVerificationEmail(
            @RequestBody @Valid UserEmailVerificationRequest request
    ) {
        sendVerificationCodeService.execute(request.email());

        return ResponseEntity.ok(
                MessageResponse.of("해당 이메일로 인증번호가 발송되었습니다, 인증코드를 입력해주세요.")
        );
    }

    @PostMapping("/signup/verification/confirm")
    public ResponseEntity<UserVerifyEmailCodeResponse> verifyEmailCode(
            @RequestBody @Valid UserVerifyEmailCodeRequest request
    ) {
        String verificationToken = verifyEmailCodeService.execute(request.email(), request.code());

        return ResponseEntity.ok(UserVerifyEmailCodeResponse.of(verificationToken));
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> signup(
            @RequestBody @Valid UserSignUpRequest request
    ) {
        userSignUpService.execute(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MessageResponse.of("회원가입 완료되었습니다. 로그인 후 이용해주세요."));
    }
}
