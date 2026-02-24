package com.command.toyvillage_server.domain.news.service;

import com.command.toyvillage_server.domain.news.domain.News;
import com.command.toyvillage_server.domain.news.domain.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsDeleteService {
    private final NewsRepository newsRepository;

    @Transactional
    public void execute(Long id){
        News news = newsRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("뉴스를 찾을수 없다"));
        log.info("뉴스 삭제: {}", news.getId());
        newsRepository.delete(news);
    }
}
