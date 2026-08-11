package com.command.toyvillage_server.domain.app.work_log.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(
        name = "tbl_work_log_template",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_work_log_template_title",
                columnNames = "template_title"
        )
)
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkLogTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_log_template_id")
    private Long id;

    @Column(name = "template_title", nullable = false, length = 100)
    private String templateTitle;

    @Column(name = "template_content", nullable = false, columnDefinition = "TEXT")
    private String templateContent;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private WorkLogTemplate(String templateTitle, String templateContent) {
        this.templateTitle = templateTitle;
        this.templateContent = templateContent;
    }

    public static WorkLogTemplate create(String templateTitle, String templateContent) {
        return new WorkLogTemplate(templateTitle, templateContent);
    }
}
