package com.command.toyvillage_server.global.security.auth;

import com.command.toyvillage_server.domain.auth.domain.Admin;
import com.command.toyvillage_server.domain.auth.domain.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String adminName) {

        Admin admin = adminRepository.findByUsername(adminName)
                .orElseThrow(() -> new UsernameNotFoundException("Admin Not Found"));

        return new CustomUserDetails(admin);
    }
}
