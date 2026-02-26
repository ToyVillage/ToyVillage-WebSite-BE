package com.command.toyvillage_server.domain.auth.domain.repository;

import com.command.toyvillage_server.domain.auth.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
