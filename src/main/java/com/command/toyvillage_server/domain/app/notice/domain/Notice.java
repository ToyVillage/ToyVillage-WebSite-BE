package com.command.toyvillage_server.domain.app.notice.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Builder
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

    @Column (nullable = false, name = "kind")
    private Kind kind;

    @Column (nullable = false, name = "content")
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public void update(String title, Kind kind, String content) {
        this.title = title;
        this.kind = kind;
        this.content = content;
    }
}
