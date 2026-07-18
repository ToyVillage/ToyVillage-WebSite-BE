package com.command.toyvillage_server.domain.app.user.presentation;

import com.command.toyvillage_server.domain.common.auth.user.presentation.dto.request.UserLoginRequest;
import com.command.toyvillage_server.domain.app.user.presentation.dto.response.UserResponse;
import com.command.toyvillage_server.domain.common.auth.user.service.UserLoginService;
import com.command.toyvillage_server.domain.app.user.service.UserQueryListService;
import com.command.toyvillage_server.domain.common.auth.common.presentation.dto.response.AccessTokenResponse;
import com.command.toyvillage_server.domain.common.auth.common.presentation.dto.response.TokenResponse;
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
    private final CookieUtil cookieUtil;

    @GetMapping()
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
}
