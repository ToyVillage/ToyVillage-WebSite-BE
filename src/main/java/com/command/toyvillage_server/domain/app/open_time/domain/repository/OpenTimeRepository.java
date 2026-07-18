package com.command.toyvillage_server.domain.app.open_time.domain.repository;

import com.command.toyvillage_server.domain.app.open_time.domain.OpenTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpenTimeRepository extends JpaRepository<OpenTime, Long> {
    List<OpenTime> findAllByOrderByIdAsc();
}
