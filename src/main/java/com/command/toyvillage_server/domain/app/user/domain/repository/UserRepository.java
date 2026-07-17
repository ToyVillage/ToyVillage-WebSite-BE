package com.command.toyvillage_server.domain.app.user.domain.repository;

import com.command.toyvillage_server.domain.app.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
