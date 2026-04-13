package com.command.toyvillage_server.domain.partnership.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_partnership")
public class Partnership {
    @Id
    @Column(name = "partnership_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "partnership_phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "partnership_name", nullable = false, length = 50)
    private String name;

    @Column(name = "partnership_email", nullable = false)
    private String email;

    @Column(name = "partnership_title",nullable = false)
    private String title;

    @Column(name = "partnership_content", nullable = false)
    private String content;

    @Column(name = "partnership_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PartnershipType type;
}
