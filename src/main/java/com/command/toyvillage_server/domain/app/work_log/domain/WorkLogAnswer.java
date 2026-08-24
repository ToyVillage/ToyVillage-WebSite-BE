package com.command.toyvillage_server.domain.app.work_log.domain;

import com.command.toyvillage_server.domain.web.file.domain.File;
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
@Table(name = "tbl_work_log_answer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkLogAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_log_answer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_log_id", nullable = false)
    private WorkLog workLog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_log_section_id", nullable = false)
    private WorkLogSection section;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_log_question_id", nullable = false)
    private WorkLogQuestion question;

    @Column(name = "answer_text", length = 500)
    private String answerText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;

    private WorkLogAnswer(
        WorkLogSection section,
        WorkLogQuestion question,
        String answerText,
        File file
    ) {
        this.section = section;
        this.question = question;
        this.answerText = answerText;
        this.file = file;
    }

    public static WorkLogAnswer create(
        WorkLogSection section,
        WorkLogQuestion question,
        String answerText,
        File file
    ) {
        return new WorkLogAnswer(section, question, answerText, file);
    }

    public boolean isFilled() {
        return file != null || (answerText != null && !answerText.isBlank());
    }

    void setWorkLog(WorkLog workLog) {
        this.workLog = workLog;
    }
}
