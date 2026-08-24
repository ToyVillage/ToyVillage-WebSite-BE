package com.command.toyvillage_server.domain.app.work_log.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "tbl_multiple_choice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MultipleChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "multiple_choice_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_log_question_id", nullable = false)
    private WorkLogTemplateQuestion question;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false, length = 30)
    private String content;

    @Column(name = "etc_choice", nullable = false)
    private boolean etc;

    private MultipleChoice(Integer number, String content, boolean etc) {
        this.number = number;
        this.content = content;
        this.etc = etc;
    }

    public static MultipleChoice create(Integer number, String content, boolean etc) {
        return new MultipleChoice(number, content, etc);
    }

    void setQuestion(WorkLogTemplateQuestion question) {
        this.question = question;
    }
}
