package com.command.toyvillage_server.domain.web.auth.domain.repository;

import com.command.toyvillage_server.domain.web.auth.domain.EmailVerification;
import org.springframework.data.repository.CrudRepository;

public interface EmailVerificationRepository extends CrudRepository<EmailVerification, String> {
}

