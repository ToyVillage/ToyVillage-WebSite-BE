package com.command.toyvillage_server.domain.app.work.domain.repository;

import com.command.toyvillage_server.domain.app.work.domain.Work;
import com.command.toyvillage_server.domain.app.work.domain.Visibility;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {

    @EntityGraph(attributePaths = "employee")
    List<Work> findAllByOrderByIdDesc();

    @EntityGraph(attributePaths = "employee")
    List<Work> findAllByVisibilityOrderByIdDesc(Visibility visibility);

    @EntityGraph(attributePaths = "employee")
    List<Work> findAllByVisibilityOrVisibilityAndVisibleTeam_IdOrderByIdDesc(
            Visibility allVisibility,
            Visibility teamVisibility,
            Long teamId
    );
}
