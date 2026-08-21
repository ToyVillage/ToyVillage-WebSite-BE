package com.command.toyvillage_server.domain.app.work_log.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "tbl_multiple_choice")
@Getter
public class MultipleChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false,  length = 30)
    private String content;
}
