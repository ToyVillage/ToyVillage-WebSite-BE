package com.command.toyvillage_server.domain.news.service;

import com.command.toyvillage_server.domain.news.domain.News;
import com.command.toyvillage_server.domain.news.domain.repository.NewsRepository;
import com.command.toyvillage_server.domain.news.exception.NewsNotFoundException;
import com.command.toyvillage_server.domain.news.presentation.dto.request.NewsRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsUpdateService {
    private final NewsRepository newsRepository;

    @Transactional
    public void execute(Long id, NewsRequest newsRequest) {
        News news = newsRepository.findById(id)
                .orElseThrow(()-> NewsNotFoundException.EXCEPTION);

        news.update(
                newsRequest.getTitle(),
                newsRequest.getDescription()
        );
        log.info("뉴스 수정 /id : {}",news.getId());
    }
}
