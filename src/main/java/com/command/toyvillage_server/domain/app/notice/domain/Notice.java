package com.command.toyvillage_server.domain.app.notice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "tbl_notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column (nullable = false, name = "kind")
    private Kind kind;

    @Column (nullable = false, name = "content")
    private String content;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Builder
    public static Notice createNotice(String title, Kind kind, String content, LocalDateTime createdAt) {
        return Notice.builder()
            .title(title)
            .kind(kind)
            .content(content)
            .createdAt(createdAt)
            .build();
    }
}
