package com.command.toyvillage_server.domain.news.presentation;

import com.command.toyvillage_server.domain.news.presentation.dto.request.NewsRequest;
import com.command.toyvillage_server.domain.news.presentation.dto.response.MessageResponse;
import com.command.toyvillage_server.domain.news.presentation.dto.response.NewsResponse;
import com.command.toyvillage_server.domain.news.service.NewsCreateService;
import com.command.toyvillage_server.domain.news.service.NewsQueryAllService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {
    private final NewsCreateService newsCreateService;
    private final NewsQueryAllService newsQueryAllService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageResponse> create(@Valid @RequestBody NewsRequest newsRequest) {
        newsCreateService.execute(newsRequest);
        return ResponseEntity.ok(new MessageResponse("뉴스가 추가되었습니다."));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NewsResponse> getQueryAllNews() {
        return newsQueryAllService.execute();
    }
}
