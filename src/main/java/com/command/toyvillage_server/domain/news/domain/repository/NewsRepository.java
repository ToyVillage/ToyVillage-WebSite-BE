package com.command.toyvillage_server.domain.news.domain.repository;

import com.command.toyvillage_server.domain.news.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {

}
