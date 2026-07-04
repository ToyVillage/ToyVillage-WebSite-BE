package com.command.toyvillage_server.domain.app.notice.domain.repository;

import com.command.toyvillage_server.domain.app.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
