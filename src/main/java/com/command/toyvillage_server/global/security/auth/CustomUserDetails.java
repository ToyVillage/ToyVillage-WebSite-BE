package com.command.toyvillage_server.global.security.auth;

import com.command.toyvillage_server.domain.auth.domain.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public record CustomUserDetails(Admin admin) implements UserDetails {
    @Override
    public String getUsername() {
        return admin.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {    //사용자의 권한 목록을 반환
        return new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
