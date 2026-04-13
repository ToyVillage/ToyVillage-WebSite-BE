package com.command.toyvillage_server.domain.popup.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pu_tbl")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PopUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pu_id")
    private Long id;

    private String content;
    private
}
