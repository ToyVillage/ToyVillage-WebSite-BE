package com.command.toyvillage_server.domain.auth.domain.repository;

import com.command.toyvillage_server.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
}
