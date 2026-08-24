package com.command.toyvillage_server.domain.app.work_log.domain;

import com.command.toyvillage_server.domain.app.work_log.domain.enums.QuestionType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "tbl_work_log_question")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkLogTemplateQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_log_question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_log_template_id", nullable = false)
    private WorkLogTemplate template;

    @Column(nullable = false, length = 80)
    private String question;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type", nullable = false)
    private QuestionType questionType;

    @Column(name = "question_order", nullable = false)
    private Integer questionOrder;

    @Column(nullable = false)
    private boolean required;

    @OrderBy("number asc")
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MultipleChoice> choices = new ArrayList<>();

    private WorkLogTemplateQuestion(
        String question,
        QuestionType questionType,
        Integer questionOrder,
        boolean required
    ) {
        this.question = question;
        this.questionType = questionType;
        this.questionOrder = questionOrder;
        this.required = required;
    }

    public static WorkLogTemplateQuestion create(
        String question,
        QuestionType questionType,
        Integer questionOrder,
        boolean required
    ) {
        return new WorkLogTemplateQuestion(question, questionType, questionOrder, required);
    }

    public void addChoice(MultipleChoice choice) {
        choices.add(choice);
        choice.setQuestion(this);
    }

    void setTemplate(WorkLogTemplate template) {
        this.template = template;
    }
}
