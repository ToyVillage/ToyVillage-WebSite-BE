package com.command.toyvillage_server.domain.auth.domain.repository;

import com.command.toyvillage_server.domain.auth.domain.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken,String> {
}
