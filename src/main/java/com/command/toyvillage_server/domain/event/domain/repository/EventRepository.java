package com.command.toyvillage_server.domain.event.domain.repository;

import com.command.toyvillage_server.domain.event.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    @EntityGraph(attributePaths = "file")
    Page<Event> findAll(Pageable pageable);
}
