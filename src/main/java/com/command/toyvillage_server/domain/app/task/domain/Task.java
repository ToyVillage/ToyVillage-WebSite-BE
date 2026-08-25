package com.command.toyvillage_server.domain.app.task.domain;

import com.command.toyvillage_server.domain.app.auth.admin.domain.AppAdmin;
import com.command.toyvillage_server.domain.app.team.domain.Team;
import com.command.toyvillage_server.domain.web.file.domain.File;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "tbl_task")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column(nullable = false, length = 100, name = "task_title")
    private String title;

    @Lob
    @Column(name = "task_content")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "assignee_type")
    private TaskAssigneeType assigneeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private AppAdmin assignee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_team_id")
    private Team assigneeTeam;

    @Column(nullable = false, name = "finish_date")
    private LocalDate finishDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @CreatedDate
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany
    @JoinTable(
            name = "tbl_task_file",
            joinColumns = @JoinColumn(name = "task_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "file_id", unique = true, nullable = false)
    )
    private List<File> files = new ArrayList<>();

    @Builder
    public Task(
            String title,
            String content,
            TaskAssigneeType assigneeType,
            AppAdmin assignee,
            Team assigneeTeam,
            LocalDate finishDate,
            TaskPriority priority,
            List<File> files
    ) {
        this.title = title;
        this.content = content;
        this.assigneeType = assigneeType;
        this.assignee = assignee;
        this.assigneeTeam = assigneeTeam;
        this.finishDate = finishDate;
        this.priority = priority;
        this.status = TaskStatus.IN_PROGRESS;
        this.files = new ArrayList<>(files);
    }

    public void update(
            String title,
            String content,
            TaskAssigneeType assigneeType,
            AppAdmin assignee,
            Team assigneeTeam,
            LocalDate finishDate,
            TaskPriority priority,
            List<File> files
    ) {
        this.title = title;
        this.content = content;
        this.assigneeType = assigneeType;
        this.assignee = assignee;
        this.assigneeTeam = assigneeTeam;
        this.finishDate = finishDate;
        this.priority = priority;
        if (files != null) {
            this.files.clear();
            this.files.addAll(files);
        }
    }

    public void updateStatus(TaskStatus status) {
        this.status = status;
    }
}
