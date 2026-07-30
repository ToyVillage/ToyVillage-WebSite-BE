package com.command.toyvillage_server.domain.app.auth.account.domain.repository;

import com.command.toyvillage_server.domain.app.auth.account.domain.AppRefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface AppRefreshTokenRepository extends CrudRepository<AppRefreshToken, String> {
}
