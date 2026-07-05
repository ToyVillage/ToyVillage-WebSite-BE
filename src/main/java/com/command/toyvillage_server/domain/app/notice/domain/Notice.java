package com.command.toyvillage_server.domain.app.notice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "kind")
    private Kind kind;

    @Column(nullable = false, name = "content")
    private String content;

    @Column(nullable = false)
    private LocalDate createdAt;

    public void update(String title, Kind kind, String content) {
        this.title = title;
        this.kind = kind;
        this.content = content;
    }

    private Notice(String title, Kind kind, String content) {
        this.title = title;
        this.kind = kind;
        this.content = content;
        this.createdAt = LocalDate.now();
    }

    public static Notice create(String title, Kind kind, String content) {
        return new Notice(title, kind, content);
    }
}
