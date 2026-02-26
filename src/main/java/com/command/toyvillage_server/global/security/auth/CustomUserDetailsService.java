package com.command.toyvillage_server.global.security.auth;

import com.command.toyvillage_server.domain.auth.domain.Admin;
import com.command.toyvillage_server.domain.auth.domain.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String organName) {

        Admin admin = adminRepository.findByOrganName(organName)
                .orElseThrow(() -> new UsernameNotFoundException("Organ Not Found"));

        return new CustomUserDetails(organ);
    }
}
