package com.command.toyvillage_server.domain.common.auth.common.domain.repository;

import com.command.toyvillage_server.domain.common.auth.common.domain.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken,String> {
}
