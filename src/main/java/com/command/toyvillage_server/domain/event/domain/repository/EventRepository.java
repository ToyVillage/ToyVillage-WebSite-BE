package com.command.toyvillage_server.domain.event.domain.repository;

import com.command.toyvillage_server.domain.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
