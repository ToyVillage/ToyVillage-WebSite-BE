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
import lombok.*;

@Getter
@Entity
@Table(name = "tbl_work_log_section")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class WorkLogSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_log_section_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_log_template_id", nullable = false)
    private WorkLogTemplate template;

    @Column(name = "section_name", nullable = false, length = 20)
    private String sectionName;

    @Column(name = "section_order", nullable = false)
    private Integer sectionOrder;

    public static WorkLogSection create(String sectionName, Integer sectionOrder) {
        return WorkLogSection.builder()
            .sectionName(sectionName)
            .sectionOrder(sectionOrder)
            .build();
    }

    void setTemplate(WorkLogTemplate template) {
        this.template = template;
    }
}
