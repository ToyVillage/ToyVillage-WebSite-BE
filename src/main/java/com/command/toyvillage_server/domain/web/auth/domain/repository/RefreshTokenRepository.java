package com.command.toyvillage_server.domain.web.auth.domain.repository;

import com.command.toyvillage_server.domain.web.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findByUsername(String username);
}
