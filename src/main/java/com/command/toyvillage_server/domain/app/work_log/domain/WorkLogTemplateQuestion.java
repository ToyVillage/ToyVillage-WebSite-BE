package com.command.toyvillage_server.domain.app.work_log.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "tbl_work_log_question")
public class WorkLogTemplateQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private
}
