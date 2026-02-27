package com.command.toyvillage_server.domain.news.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "tbl_news")
@EntityListeners(AuditingEntityListener.class)
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id", nullable = false)
    private Long id;

    @Column(name = "news_title", nullable = false)
    private String title;

    @Column(name = "news_description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @CreatedDate
    @Column(name = "news_postdate",nullable = false)
    private LocalDateTime createdDate;

    public void update(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
