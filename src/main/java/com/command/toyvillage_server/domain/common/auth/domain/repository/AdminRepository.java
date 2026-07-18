package com.command.toyvillage_server.domain.common.auth.domain.repository;

import com.command.toyvillage_server.domain.common.auth.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}
