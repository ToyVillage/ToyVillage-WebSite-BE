package com.command.toyvillage_server.global.security.jwt;

import com.command.toyvillage_server.domain.app.auth.account.domain.AppAccount;
import com.command.toyvillage_server.domain.app.auth.account.domain.AppRefreshToken;
import com.command.toyvillage_server.domain.app.auth.account.domain.AppRole;
import com.command.toyvillage_server.domain.app.auth.account.domain.repository.AppAccountRepository;
import com.command.toyvillage_server.domain.app.auth.account.domain.repository.AppRefreshTokenRepository;
import com.command.toyvillage_server.domain.web.auth.admin.domain.repository.WebAdminRepository;
import com.command.toyvillage_server.domain.web.auth.admin.domain.repository.WebRefreshTokenRepository;
import com.command.toyvillage_server.global.security.auth.AppAccountDetails;
import com.command.toyvillage_server.global.security.auth.AppAccountDetailsService;
import com.command.toyvillage_server.global.security.auth.WebAdminDetailsService;
import com.command.toyvillage_server.global.security.exception.InvalidTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SeparatedJwtTokenProviderTest {
    private static final String WEB_SECRET =
            "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef";
    private static final String APP_SECRET =
            "abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789";

    @Mock
    private WebAdminRepository webAdminRepository;

    @Mock
    private WebRefreshTokenRepository webRefreshTokenRepository;

    @Mock
    private WebAdminDetailsService webAdminDetailsService;

    @Mock
    private AppAccountRepository appAccountRepository;

    @Mock
    private AppRefreshTokenRepository appRefreshTokenRepository;

    @Mock
    private AppAccountDetailsService appAccountDetailsService;

    private WebJwtTokenProvider webJwtTokenProvider;
    private AppJwtTokenProvider appJwtTokenProvider;
    private AppAccount employee;

    @BeforeEach
    void setUp() {
        webJwtTokenProvider = new WebJwtTokenProvider(
                new WebJwtProperties(WEB_SECRET, 3_600_000L, 604_800_000L),
                webAdminRepository,
                webRefreshTokenRepository,
                webAdminDetailsService
        );
        appJwtTokenProvider = new AppJwtTokenProvider(
                new AppJwtProperties(APP_SECRET, 3_600_000L, 604_800_000L),
                appAccountRepository,
                appRefreshTokenRepository,
                appAccountDetailsService
        );
        employee = AppAccount.builder()
                .id(1L)
                .username("employee01")
                .name("직원")
                .password("encoded-password")
                .role(AppRole.EMPLOYEE)
                .build();
    }

    @Test
    void 앱_토큰은_앱_Principal로만_인증된다() {
        TokenPair tokens = appJwtTokenProvider.issue(employee);
        when(appAccountDetailsService.loadUserByUsername("employee01"))
                .thenReturn(new AppAccountDetails(employee));

        Authentication authentication = appJwtTokenProvider.getAuthentication(tokens.accessToken());

        assertEquals("employee01", authentication.getName());
        assertEquals(
                "ROLE_EMPLOYEE",
                authentication.getAuthorities().iterator().next().getAuthority()
        );
        assertThrows(
                InvalidTokenException.class,
                () -> webJwtTokenProvider.getAuthentication(tokens.accessToken())
        );
    }

    @Test
    void Refresh_Token으로_일반_API_인증을_할_수_없다() {
        TokenPair tokens = appJwtTokenProvider.issue(employee);

        assertThrows(
                InvalidTokenException.class,
                () -> appJwtTokenProvider.getAuthentication(tokens.refreshToken())
        );
    }

    @Test
    void 재발급하면_저장된_Refresh_Token을_검증하고_회전한다() {
        TokenPair oldTokens = appJwtTokenProvider.issue(employee);
        AppRefreshToken storedToken = AppRefreshToken.create(
                "employee01",
                oldTokens.refreshToken(),
                604_800_000L
        );
        when(appRefreshTokenRepository.findById("employee01"))
                .thenReturn(Optional.of(storedToken));
        when(appAccountRepository.findByUsername("employee01"))
                .thenReturn(Optional.of(employee));

        TokenPair newTokens = appJwtTokenProvider.reissue(oldTokens.refreshToken());

        assertNotEquals(oldTokens.accessToken(), newTokens.accessToken());
        assertNotEquals(oldTokens.refreshToken(), newTokens.refreshToken());
        verify(appRefreshTokenRepository).delete(storedToken);
        verify(appRefreshTokenRepository, times(2)).save(any(AppRefreshToken.class));
    }

    @Test
    void DB에서_권한이_바뀐_기존_토큰은_거부한다() {
        TokenPair tokens = appJwtTokenProvider.issue(employee);
        AppAccount changedAccount = AppAccount.builder()
                .id(1L)
                .username("employee01")
                .name("직원")
                .password("encoded-password")
                .role(AppRole.APP_ADMIN)
                .build();
        when(appAccountDetailsService.loadUserByUsername("employee01"))
                .thenReturn(new AppAccountDetails(changedAccount));

        assertThrows(
                InvalidTokenException.class,
                () -> appJwtTokenProvider.getAuthentication(tokens.accessToken())
        );
    }
}
