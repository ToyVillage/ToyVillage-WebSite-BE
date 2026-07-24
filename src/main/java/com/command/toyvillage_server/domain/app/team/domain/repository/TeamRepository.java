package com.command.toyvillage_server.domain.app.team.domain.repository;

import com.command.toyvillage_server.domain.app.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
