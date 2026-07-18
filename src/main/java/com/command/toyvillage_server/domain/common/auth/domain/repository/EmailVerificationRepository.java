package com.command.toyvillage_server.domain.common.auth.domain.repository;

import com.command.toyvillage_server.domain.common.auth.domain.EmailVerification;
import org.springframework.data.repository.CrudRepository;

public interface EmailVerificationRepository extends CrudRepository<EmailVerification, String> {
}

