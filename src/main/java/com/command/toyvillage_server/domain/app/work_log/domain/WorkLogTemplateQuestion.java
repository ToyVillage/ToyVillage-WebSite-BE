package com.command.toyvillage_server.domain.app.work_log.domain;

import com.command.toyvillage_server.domain.app.work_log.domain.enums.QuestionType;
import com.command.toyvillage_server.domain.web.file.domain.File;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "tbl_work_log_question")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class WorkLogTemplateQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "work_log_template_id")
    private WorkLogTemplate workLogTemplate;

    @Column(nullable = false, length = 80)
    private String question;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type", nullable = false)
    private QuestionType questionType;

    @Column(name = "short_text", length = 100)
    private String shortText;

    @Column(name = "long_text", length = 500)
    private String longText;

    @Column(name = "nultiple_choice", length = 50)
    private String multipleChoice;

    @Column(name = "check_box", length = 50)
    private String checkBox;

    @Column(name = "drop_down", length = 50)
    private String dropDown;

    @JoinColumn(name = "file_upload")
    @OneToOne(fetch = FetchType.LAZY)
    private File fileUpload;
}
