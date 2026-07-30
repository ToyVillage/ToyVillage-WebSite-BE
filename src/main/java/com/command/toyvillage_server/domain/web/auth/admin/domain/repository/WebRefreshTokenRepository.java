package com.command.toyvillage_server.domain.web.auth.admin.domain.repository;

import com.command.toyvillage_server.domain.web.auth.admin.domain.WebRefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface WebRefreshTokenRepository extends CrudRepository<WebRefreshToken, String> {
}
