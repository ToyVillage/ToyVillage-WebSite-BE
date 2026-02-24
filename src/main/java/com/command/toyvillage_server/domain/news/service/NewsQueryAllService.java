package com.command.toyvillage_server.domain.news.service;

import com.command.toyvillage_server.domain.news.domain.News;
import com.command.toyvillage_server.domain.news.domain.repository.NewsRepository;
import com.command.toyvillage_server.domain.news.presentation.dto.response.NewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsQueryAllService {
    private final NewsRepository newsRepository;

    @Transactional(readOnly = true)
    public List<NewsResponse> execute() {
        return newsRepository.findAll()
                .stream()
                .map(NewsResponse::from)
                .collect(Collectors.toList());
    }
}
