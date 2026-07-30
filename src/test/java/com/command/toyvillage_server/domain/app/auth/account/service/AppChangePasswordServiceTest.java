package com.command.toyvillage_server.domain.app.auth.account.service;

import com.command.toyvillage_server.domain.app.auth.account.domain.AppAccount;
import com.command.toyvillage_server.domain.app.auth.account.domain.AppRole;
import com.command.toyvillage_server.domain.app.auth.account.domain.repository.AppAccountRepository;
import com.command.toyvillage_server.domain.app.auth.account.presentation.dto.request.AppChangePasswordRequest;
import com.command.toyvillage_server.global.error.exception.ToyVillageException;
import com.command.toyvillage_server.global.security.auth.AppAccountDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppChangePasswordServiceTest {

    @Mock
    private AppAccountRepository appAccountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private AppChangePasswordService appChangePasswordService;
    private AppAccount account;

    @BeforeEach
    void setUp() {
        appChangePasswordService = new AppChangePasswordService(
                appAccountRepository,
                passwordEncoder
        );
        account = AppAccount.builder()
                .id(1L)
                .username("employee01")
                .name("직원")
                .password("old-encoded-password")
                .role(AppRole.EMPLOYEE)
                .build();

        AppAccountDetails details = new AppAccountDetails(account);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities())
        );
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void 현재_비밀번호를_확인하고_새_비밀번호로_변경한다() {
        AppChangePasswordRequest request = new AppChangePasswordRequest("old-password", "new-password");
        when(appAccountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(passwordEncoder.matches("old-password", "old-encoded-password")).thenReturn(true);
        when(passwordEncoder.encode("new-password")).thenReturn("new-encoded-password");

        appChangePasswordService.execute(request);

        assertEquals("new-encoded-password", account.getPassword());
    }

    @Test
    void 현재_비밀번호가_다르면_변경하지_않는다() {
        AppChangePasswordRequest request = new AppChangePasswordRequest("wrong-password", "new-password");
        when(appAccountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(passwordEncoder.matches("wrong-password", "old-encoded-password")).thenReturn(false);

        assertThrows(ToyVillageException.class, () -> appChangePasswordService.execute(request));

        assertEquals("old-encoded-password", account.getPassword());
        verify(passwordEncoder, never()).encode("new-password");
    }
}
