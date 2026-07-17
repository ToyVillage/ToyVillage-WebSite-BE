package com.command.toyvillage_server.domain.app.user.domain.repository;

import com.command.toyvillage_server.domain.app.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
}
