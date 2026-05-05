package com.command.toyvillage_server.domain.popup.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_pu")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PopUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pu_id")
    private Long id;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String popupImage;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @Column(nullable = false)
    private Integer priority;

    public void update(
            String popupImage,
            LocalDate expirationDate,
            Integer priority
    ){
        this.popupImage = popupImage;
        this.expirationDate = expirationDate;
        this.priority = priority;
    }
}
