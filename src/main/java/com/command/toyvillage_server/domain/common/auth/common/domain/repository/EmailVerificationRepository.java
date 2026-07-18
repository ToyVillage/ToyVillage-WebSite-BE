package com.command.toyvillage_server.domain.common.auth.common.domain.repository;

import com.command.toyvillage_server.domain.common.auth.common.domain.EmailVerification;
import org.springframework.data.repository.CrudRepository;

public interface EmailVerificationRepository extends CrudRepository<EmailVerification, String> {
}

