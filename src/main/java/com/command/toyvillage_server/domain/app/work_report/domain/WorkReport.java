package com.command.toyvillage_server.domain.app.work_report.domain;

import com.command.toyvillage_server.domain.app.task.domain.Task;
import com.command.toyvillage_server.domain.web.file.domain.File;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_work_report")
public class WorkReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_report_id",nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(nullable = false)
    private String content;

    private String note;

    @OneToMany
    @JoinTable(
            name = "tbl_document_file",
            joinColumns = @JoinColumn(name = "document_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "file_id", unique = true, nullable = false)
    )
    private List<File> files;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;

    public void approve(){
        this.status = Status.APPROVED;
    }
    public void reject(){
        this.status = Status.REJECTED;
    }
}
