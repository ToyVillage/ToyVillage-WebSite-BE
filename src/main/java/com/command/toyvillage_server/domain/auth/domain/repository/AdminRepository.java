package com.command.toyvillage_server.domain.auth.domain.repository;

import com.command.toyvillage_server.domain.auth.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}
