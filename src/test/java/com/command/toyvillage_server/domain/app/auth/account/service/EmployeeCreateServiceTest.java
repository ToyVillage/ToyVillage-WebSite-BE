package com.command.toyvillage_server.domain.app.auth.account.service;

import com.command.toyvillage_server.domain.app.auth.account.domain.AppAccount;
import com.command.toyvillage_server.domain.app.auth.account.domain.AppRole;
import com.command.toyvillage_server.domain.app.auth.account.domain.repository.AppAccountRepository;
import com.command.toyvillage_server.domain.app.auth.account.exception.AppAccountAlreadyExistsException;
import com.command.toyvillage_server.domain.app.auth.account.presentation.dto.request.EmployeeCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeCreateServiceTest {

    @Mock
    private AppAccountRepository appAccountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private EmployeeCreateService employeeCreateService;

    @BeforeEach
    void setUp() {
        employeeCreateService = new EmployeeCreateService(appAccountRepository, passwordEncoder);
    }

    @Test
    void 아이디와_이름으로_직원_계정을_생성한다() {
        EmployeeCreateRequest request = new EmployeeCreateRequest("employee01", "홍길동");
        when(appAccountRepository.existsByUsername("employee01")).thenReturn(false);
        when(passwordEncoder.encode("employee01")).thenReturn("encoded-password");

        employeeCreateService.execute(request);

        ArgumentCaptor<AppAccount> accountCaptor = ArgumentCaptor.forClass(AppAccount.class);
        verify(appAccountRepository).save(accountCaptor.capture());

        AppAccount savedAccount = accountCaptor.getValue();
        assertEquals("employee01", savedAccount.getUsername());
        assertEquals("홍길동", savedAccount.getName());
        assertEquals("encoded-password", savedAccount.getPassword());
        assertEquals(AppRole.EMPLOYEE, savedAccount.getRole());
    }

    @Test
    void 이미_존재하는_아이디면_직원_계정을_생성하지_않는다() {
        EmployeeCreateRequest request = new EmployeeCreateRequest("employee01", "홍길동");
        when(appAccountRepository.existsByUsername("employee01")).thenReturn(true);

        AppAccountAlreadyExistsException exception = assertThrows(
                AppAccountAlreadyExistsException.class,
                () -> employeeCreateService.execute(request)
        );

        assertSame(AppAccountAlreadyExistsException.EXCEPTION, exception);
        verify(passwordEncoder, never()).encode("employee01");
        verify(appAccountRepository, never()).save(org.mockito.ArgumentMatchers.any());
    }
}
