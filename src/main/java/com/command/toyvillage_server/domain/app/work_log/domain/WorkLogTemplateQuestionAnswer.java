package com.command.toyvillage_server.domain.app.work_log.domain;

import com.command.toyvillage_server.domain.app.work_log.domain.enums.QuestionType;
import com.command.toyvillage_server.domain.web.file.domain.File;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "tbl_work_log_question_answer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class WorkLogTemplateQuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_log_template_question_id", nullable = false)
    private WorkLogTemplateQuestion workLogTemplateQuestion;

    @Column(nullable = false, length = 80)
    private String questionAnswer;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type_answer", nullable = false)
    private QuestionType questionTypeAnswer;

    @Column(name = "short_text_answer", length = 100)
    private String shortTextAnswer;

    @Column(name = "long_text_answer", length = 500)
    private String longTextAnswer;

    @Column(name = "nultiple_choice_answer", length = 50)
    private String multipleChoiceAnswer;

    @Column(name = "check_box_answer", length = 50)
    private String checkBoxAnswer;

    @Column(name = "drop_down_answer", length = 50)
    private String dropDownAnswer;

    @JoinColumn(name = "file_upload_answer")
    @OneToOne(fetch = FetchType.LAZY)
    private File fileUploadAnswer;
}
