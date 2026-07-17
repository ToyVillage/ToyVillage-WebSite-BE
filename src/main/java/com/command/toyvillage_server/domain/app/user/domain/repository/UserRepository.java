package com.command.toyvillage_server.domain.app.user.domain.repository;

import com.command.toyvillage_server.domain.app.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findById(Long id);
}
