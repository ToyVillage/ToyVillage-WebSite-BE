package com.command.toyvillage_server.domain.popup.domain;

import com.command.toyvillage_server.domain.popup.domain.enums.ContentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_pu")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PopUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pu_id")
    private Long id;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentType contentType;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @Column(nullable = false)
    private Integer priority;

    public void update(
            String content,
            ContentType contentType,
            LocalDate expirationDate,
            Integer priority
    ){
        this.content = content;
        this.contentType = contentType;
        this.expirationDate = expirationDate;
        this.priority = priority;
    }
}
