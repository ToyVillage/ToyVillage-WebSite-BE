package com.command.toyvillage_server.domain.animal.domain.repository;

import com.command.toyvillage_server.domain.animal.domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
