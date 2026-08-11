package com.command.toyvillage_server.domain.app.work_log.domain;

import com.command.toyvillage_server.domain.app.auth.admin.domain.AppAdmin;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "tbl_work_log")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_admin_id", nullable = false)
    private AppAdmin appAdmin;

    @Column(name = "template_title", nullable = false, length = 100)
    private String templateTitle;

    @Column(name = "template_content", nullable = false, columnDefinition = "TEXT")
    private String templateContent;

    @CreatedDate
    @Column(name = "write_at", nullable = false, updatable = false)
    private LocalDate writeAt;

    private WorkLog(AppAdmin appAdmin, String templateTitle, String templateContent) {
        this.appAdmin = appAdmin;
        this.templateTitle = templateTitle;
        this.templateContent = templateContent;
    }

    public static WorkLog create(AppAdmin appAdmin, String templateTitle, String templateContent) {
        return new WorkLog(appAdmin, templateTitle, templateContent);
    }
}
