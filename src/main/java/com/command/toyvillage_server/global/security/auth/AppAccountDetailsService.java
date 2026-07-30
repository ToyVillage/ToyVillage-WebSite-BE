package com.command.toyvillage_server.global.security.auth;

import com.command.toyvillage_server.domain.app.auth.account.domain.AppAccount;
import com.command.toyvillage_server.domain.app.auth.account.domain.repository.AppAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppAccountDetailsService implements UserDetailsService {
    private final AppAccountRepository appAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppAccount account = appAccountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("App account not found"));

        return new AppAccountDetails(account);
    }
}
