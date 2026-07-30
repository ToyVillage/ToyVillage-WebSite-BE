package com.command.toyvillage_server.domain.app.auth.account.domain.repository;

import com.command.toyvillage_server.domain.app.auth.account.domain.AppAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppAccountRepository extends JpaRepository<AppAccount, Long> {
    Optional<AppAccount> findByUsername(String username);

    boolean existsByUsername(String username);
}
