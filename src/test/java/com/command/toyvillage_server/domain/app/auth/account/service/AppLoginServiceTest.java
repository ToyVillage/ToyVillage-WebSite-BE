package com.command.toyvillage_server.domain.app.auth.account.service;

import com.command.toyvillage_server.domain.app.auth.account.domain.AppAccount;
import com.command.toyvillage_server.domain.app.auth.account.domain.AppRole;
import com.command.toyvillage_server.domain.app.auth.account.domain.repository.AppAccountRepository;
import com.command.toyvillage_server.domain.app.auth.account.presentation.dto.request.AppLoginRequest;
import com.command.toyvillage_server.domain.app.auth.account.presentation.dto.response.AppLoginResponse;
import com.command.toyvillage_server.global.security.exception.LoginInfoNotMatchedException;
import com.command.toyvillage_server.global.security.jwt.AppJwtTokenProvider;
import com.command.toyvillage_server.global.security.jwt.TokenPair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppLoginServiceTest {

    @Mock
    private AppAccountRepository appAccountRepository;

    @Mock
    private AppJwtTokenProvider appJwtTokenProvider;

    @Mock
    private PasswordEncoder passwordEncoder;

    private AppLoginService appLoginService;

    @BeforeEach
    void setUp() {
        appLoginService = new AppLoginService(
                appAccountRepository,
                appJwtTokenProvider,
                passwordEncoder
        );
    }

    @Test
    void 앱_관리자와_직원은_같은_API로_로그인한다() {
        AppAccount account = AppAccount.builder()
                .id(1L)
                .username("admin01")
                .name("관리자")
                .password("encoded-password")
                .role(AppRole.APP_ADMIN)
                .build();
        AppLoginRequest request = new AppLoginRequest("admin01", "password");
        TokenPair tokens = TokenPair.of("access-token", "refresh-token");

        when(appAccountRepository.findByUsername("admin01")).thenReturn(Optional.of(account));
        when(passwordEncoder.matches("password", "encoded-password")).thenReturn(true);
        when(appJwtTokenProvider.issue(account)).thenReturn(tokens);

        AppLoginResponse response = appLoginService.execute(request);

        assertEquals("access-token", response.accessToken());
        assertEquals("refresh-token", response.refreshToken());
        assertEquals("관리자", response.name());
        assertEquals("APP_ADMIN", response.role());
    }

    @Test
    void 비밀번호가_다르면_로그인에_실패한다() {
        AppAccount account = AppAccount.builder()
                .id(1L)
                .username("employee01")
                .name("직원")
                .password("encoded-password")
                .role(AppRole.EMPLOYEE)
                .build();
        AppLoginRequest request = new AppLoginRequest("employee01", "wrong-password");

        when(appAccountRepository.findByUsername("employee01")).thenReturn(Optional.of(account));
        when(passwordEncoder.matches("wrong-password", "encoded-password")).thenReturn(false);

        LoginInfoNotMatchedException exception = assertThrows(
                LoginInfoNotMatchedException.class,
                () -> appLoginService.execute(request)
        );

        assertSame(LoginInfoNotMatchedException.EXCEPTION, exception);
        verify(appJwtTokenProvider, never()).issue(account);
    }
}
