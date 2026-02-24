package com.command.toyvillage_server.domain.news.service;

import com.command.toyvillage_server.domain.news.domain.News;
import com.command.toyvillage_server.domain.news.domain.repository.NewsRepository;
import com.command.toyvillage_server.domain.news.presentation.dto.request.NewsRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsCreateService {
    private final NewsRepository newsRepository;

    @Transactional
    public void execute(NewsRequest newsRequest) {
        News news = News.builder()
                .title(newsRequest.getTitle())
                .description(newsRequest.getDescription())
                .build();
        log.info("뉴스 생성됨: {}", news.getId());
        newsRepository.save(news);
    }
}
