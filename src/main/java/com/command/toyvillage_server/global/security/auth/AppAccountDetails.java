package com.command.toyvillage_server.global.security.auth;

import com.command.toyvillage_server.domain.app.auth.account.domain.AppAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public record AppAccountDetails(AppAccount appAccount) implements UserDetails {

    @Override
    public String getUsername() {
        return appAccount.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + appAccount.getRole().name()));
    }

    @Override
    public String getPassword() {
        return appAccount.getPassword();
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

    public Long getId() {
        return appAccount.getId();
    }
}
