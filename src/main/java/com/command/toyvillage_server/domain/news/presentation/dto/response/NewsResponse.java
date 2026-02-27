package com.command.toyvillage_server.domain.news.presentation.dto.response;

import com.command.toyvillage_server.domain.news.domain.News;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class NewsResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime postdate;

    public static NewsResponse from(News news) {
        return new NewsResponse(
                news.getId(),
                news.getTitle(),
                news.getDescription(),
                news.getCreatedDate()
        );
    }
}
