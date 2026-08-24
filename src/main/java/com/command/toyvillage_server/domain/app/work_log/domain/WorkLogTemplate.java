package com.command.toyvillage_server.domain.app.work_log.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "tbl_work_log_template")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkLogTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_log_template_id")
    private Long id;

    @Column(name = "template_title", nullable = false, unique = true, length = 50)
    private String templateTitle;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OrderBy("questionOrder asc")
    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkLogQuestion> questions = new ArrayList<>();

    @OrderBy("sectionOrder asc")
    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkLogSection> sections = new ArrayList<>();

    private WorkLogTemplate(String templateTitle) {
        this.templateTitle = templateTitle;
    }

    public static WorkLogTemplate create(String templateTitle) {
        return new WorkLogTemplate(templateTitle);
    }

    public void addQuestion(WorkLogQuestion question) {
        questions.add(question);
        question.setTemplate(this);
    }

    public void addSection(WorkLogSection section) {
        sections.add(section);
        section.setTemplate(this);
    }
}
